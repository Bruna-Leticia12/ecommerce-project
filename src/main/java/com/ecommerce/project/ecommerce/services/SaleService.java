package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.QueryDateDTO;
import com.ecommerce.project.ecommerce.dto.SaleItemDTO;
import com.ecommerce.project.ecommerce.entities.Product;
import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.entities.SaleItem;
import com.ecommerce.project.ecommerce.entities.User;
import com.ecommerce.project.ecommerce.entities.pk.SaleItemPK;
import com.ecommerce.project.ecommerce.enums.SaleStatus;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import com.ecommerce.project.ecommerce.repositories.SaleItemRepository;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import com.ecommerce.project.ecommerce.repositories.UserRepository;
import com.ecommerce.project.ecommerce.services.exceptions.InsufficientStockException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import com.ecommerce.project.ecommerce.services.exceptions.UnavailableOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "sales")
public class SaleService {

    @Autowired
    private SaleRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private SaleItemService saleItemService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentService paymentService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SaleService(SaleRepository repository, ProductRepository productRepository, UserService userService, ProductService productService, SaleItemRepository saleItemRepository) {
        super();
        this.repository = repository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.productService = productService;
        this.saleItemRepository = saleItemRepository;
    }

    //Listar todas as vendas
    @Cacheable(key="#root.methodName")
    public List<Sale> findAll() {
        return repository.findAll();
    }

    //Listar uma venda por id
    public Sale findById(Integer id) {
        Optional<Sale> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //Listar venda por data
    public List<Sale> queryDate(QueryDateDTO dto) {
        List<Sale> list = repository.findBySaleDateBetween(dto.getInitialDate(), dto.getFinalDate());
        return list;
    }

    //Inserir uma venda
    @CacheEvict(allEntries = true)
    public Sale create(Integer userId) {
        Sale sale = new Sale();
        Instant paymentDate = Instant.now();
        sale.setSaleDate(paymentDate);

        User user = userService.findById(userId);
        user = entityManager.merge(user); // Garantir que a entidade User está gerenciada

        sale.setClient(user);
        return repository.save(sale);
    }

    //Inserir um item na venda
    @CacheEvict(allEntries = true)
    public Sale insertItem(Integer saleId, SaleItemDTO dto) {
        Sale sale = findById(saleId);
        if (sale.getSaleStatus() == SaleStatus.WAITING_PAYMENT) {
            Product product = productService.findById(dto.productId());

            if (product.getStockQuantity() >= dto.quantity()) {
                SaleItem item = saleItemService.insert(sale, product, dto.quantity());
                sale.getItems().add(item);
                productService.removeStockItem(dto.productId(), dto.quantity());
                return repository.save(sale);
            } else {
                throw new InsufficientStockException(dto.productId());
            }
        } else {
            throw new UnavailableOrderException(saleId);
        }
    }

    //Remover um item da venda
    @CacheEvict(allEntries = true)
    public Sale itemRemove(Integer saleId, Integer productId) {
        Sale sale = repository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with id: " + saleId));

        SaleItem saleItem = sale.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in sale: " + productId));

        sale.getItems().remove(saleItem);
        sale.setTotal(calculateTotal(sale));

        return repository.save(sale);
    }

    //Atualizar venda
    @CacheEvict(allEntries = true)
    public Sale updateQuantity(Integer saleId, SaleItemDTO dto) {
        Sale sale = findById(saleId);

        if (sale.getSaleStatus() == SaleStatus.WAITING_PAYMENT) {
            Product product = productService.findById(dto.productId());

            SaleItemPK itemVendaPK = new SaleItemPK();
            itemVendaPK.setSale(sale);
            itemVendaPK.setProduct(product);

            SaleItem saleItem = saleItemService.findById(itemVendaPK);

            SaleItem updatedSaleItem = new SaleItem();
            updatedSaleItem.setQuantity(dto.quantity());
            updatedSaleItem.setPrice(saleItem.getPrice());

            if (dto.quantity() > saleItem.getQuantity()) {
                if (product.getStockQuantity() >= dto.quantity()) {
                    productService.removeStockItem(dto.productId(), (dto.quantity() - saleItem.getQuantity()));
                    saleItemService.updateData(itemVendaPK, updatedSaleItem);

                    return repository.save(sale);
                } else {
                    throw new InsufficientStockException(dto.productId());
                }
            } else {
                productService.includeStockItem(dto.productId(), (saleItem.getQuantity() - dto.quantity()));
                saleItemService.updateData(itemVendaPK, updatedSaleItem);

                return repository.save(sale);
            }
        } else {
            throw new UnavailableOrderException(saleId);
        }
    }

    private double calculateTotal(Sale sale) {
        double total = 0.0;
        for (SaleItem item : sale.getItems()) {
            total += item.getSubTotal();
        }
        return total;
    }

    //Cancelar uma venda
    @CacheEvict(allEntries = true)
    public Sale cancelSale(Integer saleId) {
        Sale sale = repository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Venda já cancelada: " + saleId));

        if (sale.getSaleStatus() == SaleStatus.CANCELED) {
            throw new IllegalStateException("SVenda já cancelada.");
        }
        if (sale.getSaleStatus() == SaleStatus.CONFIRMED) {
            sale.setSaleStatus(SaleStatus.CANCELED);
            sale.setTotal(calculateTotal(sale));
            return repository.save(sale);
        } else {
            throw new IllegalStateException("Não é possível cancelar, venda não confirmada.");
        }
    }


}
