package com.ecommerce.project.ecommerce.services;

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
import com.ecommerce.project.ecommerce.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    //Listar todas as vendas
    @Cacheable(key = "#root.methodName")
    public List<Sale> findAll() {
        return repository.findAll();
    }

    //Listar uma venda por id
    public Sale findById(Integer id) {
        Optional<Sale> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //Inserir uma venda
    @CacheEvict(allEntries = true)
    public Sale create(Integer userId) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        Sale sale = new Sale();
        sale.setSaleDate(Instant.now());
        sale.setClient(user);
        sale.setSaleStatus(SaleStatus.WAITING_PAYMENT);
        return repository.save(sale);
    }

    //Inserir item
    @CacheEvict(allEntries = true)
    public Sale insertItem(Integer saleId, SaleItemDTO dto) {
        Sale sale = findById(saleId);
        if (sale == null) {
            throw new ResourceNotFoundException("Sale not found with id " + saleId);
        }
        if (sale.getSaleStatus() == SaleStatus.WAITING_PAYMENT) {
            Product product = productService.findById(dto.productId());
            if (product == null) {
                throw new ResourceNotFoundException("Product not found with id " + dto.productId());
            }
            if (product.getStockQuantity() >= dto.quantity()) {
                SaleItem item = saleItemService.insert(sale, product, dto.quantity());
                sale.getItems().add(item);
                productService.removeStockItem(dto.productId(), dto.quantity());
                return repository.save(sale);
            } else {
                throw new InsufficientStockException("Insufficient stock for product");
            }
        } else {
            throw new UnavailableOrderException("Order with id " + saleId + " is not in WAITING_PAYMENT status.");
        }
    }

    //Remover um item da venda
    @CacheEvict(allEntries = true)
    public Sale itemRemove(Integer saleId, Integer productId) {
        Sale sale = findById(saleId);
        if (sale.getSaleStatus() == SaleStatus.WAITING_PAYMENT) {
            Product product = productService.findById(productId);
            SaleItemPK saleItemPK = new SaleItemPK();
            saleItemPK.setSale(sale);
            saleItemPK.setProduct(product);
            SaleItem item = saleItemService.findById(saleItemPK);
            productService.includeStockItem(productId, item.getQuantity());
            saleItemService.delete(saleItemPK);
            return repository.save(sale);
        } else {
            throw new UnavailableOrderException(saleId);
        }
    }

    // Atualizar venda
    @CacheEvict(allEntries = true)
    public Sale updateQuantity(Integer saleId, SaleItemDTO dto) {
        Sale sale = findById(saleId);
        if (sale.getSaleStatus() == SaleStatus.WAITING_PAYMENT) {
            if (sale.getItems().isEmpty()) {
                throw new EmptySaleException("Sale cannot be concluded without items.");
            }
            Product product = productService.findById(dto.productId());
            SaleItemPK itemVendaPK = new SaleItemPK();
            itemVendaPK.setSale(sale);
            itemVendaPK.setProduct(product);
            SaleItem itemVenda = saleItemService.findById(itemVendaPK);
            SaleItem newItem = new SaleItem();
            newItem.setQuantity(dto.quantity());
            newItem.setPrice(itemVenda.getPrice());
            if (dto.quantity() > itemVenda.getQuantity()) {
                if (product.getStockQuantity() >= dto.quantity()) {
                    productService.removeStockItem(dto.productId(), (dto.quantity() - itemVenda.getQuantity()));
                    saleItemService.updateData(itemVendaPK, newItem);
                    return repository.save(sale);
                } else {
                    throw new InsufficientStockException("Insufficient stock for product");
                }
            } else {
                productService.includeStockItem(dto.productId(), (itemVenda.getQuantity() - dto.quantity()));
                saleItemService.updateData(itemVendaPK, newItem);
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

    // Confirmar uma venda
    @CacheEvict(allEntries = true)
    public Sale confirmSale(Integer id) {
        Sale sale = repository.getReferenceById(id);
        if (sale.getSaleStatus() == SaleStatus.WAITING_PAYMENT && !sale.getItems().isEmpty()) {
            sale.setSaleStatus(SaleStatus.CONFIRMED);
            return repository.save(sale);
        } else if (sale.getSaleStatus() == SaleStatus.CANCELED) {
            throw new ConfirmSaleException("Canceled sale cannot be confirmed.");
        } else {
            throw new ConfirmSaleException("Cannot confirm a sale with no items.");
        }
    }

    // Cancelar uma venda
    @CacheEvict(allEntries = true)
    public Sale cancelSale(Integer id) {
        Sale cancel = repository.getReferenceById(id);
        if (cancel.getSaleStatus() == SaleStatus.WAITING_PAYMENT) {
            cancel.setSaleStatus(SaleStatus.CANCELED);
            Set<SaleItem> items = cancel.getItems();
            for (SaleItem item : items) {
                productService.includeStockItem(item.getProduct().getId(), item.getQuantity());
            }
            return repository.save(cancel);
        } else if (cancel.getSaleStatus() == SaleStatus.CONFIRMED) {
            throw new CanceledOrderException("Cannot cancel confirmed sale.");
        } else if (cancel.getSaleStatus() == SaleStatus.CANCELED) {
            throw new CanceledOrderException("Sale is already canceled.");
        }
        return cancel;
    }
}

