package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.SaleItemDTO;
import com.ecommerce.project.ecommerce.entities.Product;
import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.entities.SaleItem;
import com.ecommerce.project.ecommerce.enums.SaleStatus;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import com.ecommerce.project.ecommerce.repositories.SaleItemRepository;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import com.ecommerce.project.ecommerce.services.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.project.ecommerce.entities.User;

import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Service
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
    public SaleService(SaleRepository repository, ProductRepository productRepository, UserService userService, ProductService productService, SaleItemRepository saleItemRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.productService = productService;
        this.saleItemRepository = saleItemRepository;
    }

    public List<Sale> findAll() {
        return repository.findAll();
    }

    public Sale findById(Long id) {
        Optional<Sale> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Sale create(Long clientId) {
        User client = userService.findById(clientId);
        Sale sale = new Sale(null, Instant.now(), SaleStatus.WAITING_PAYMENT, client);
        return repository.save(sale);
    }

    public Sale insertItem(Long saleId, Long productId, int quantity) {
        Sale sale = repository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with id: " + saleId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        SaleItem saleItem = new SaleItem(sale, product, quantity, product.getPrice());
        sale.getItems().add(saleItem);

        sale.setTotal(calculateTotal(sale));

        return repository.save(sale);
    }

    public Sale itemRemove(Long saleId, Long productId) {
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

    public Sale updateQuantity(Long saleId, SaleItemDTO dto) {
        Sale sale = repository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with id: " + saleId));

        SaleItem saleItem = sale.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(dto.getProductId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in sale: " + dto.getProductId()));

        saleItem.setQuantity(dto.getQuantity());
        saleItem.setSubTotal(saleItem.getProduct().getPrice() * dto.getQuantity());

        sale.setTotal(calculateTotal(sale));

        return repository.save(sale);
    }

    private double calculateTotal(Sale sale) {
        double total = 0.0;
        for (SaleItem item : sale.getItems()) {
            total += item.getSubTotal();
        }
        return total;
    }


    // Método para cancelar uma venda
    public Sale cancelSale(Long saleId) {
        Sale sale = repository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with id: " + saleId));

        if (sale.getSaleStatus() == SaleStatus.CANCELED) {
            throw new IllegalStateException("Sale is already canceled.");
        }

        if (sale.getSaleStatus() == SaleStatus.CONFIRMED) {
            sale.setSaleStatus(SaleStatus.CANCELED);

            // Atualizar o total da venda (se necessário)
            sale.setTotal(calculateTotal(sale));

            return repository.save(sale);
        } else {
            throw new IllegalStateException("Cannot cancel a sale that is not confirmed.");
        }
    }

}



//    //gerar pagamento de vendas
//    public Venda pagar(Integer vendaId) {
//                venda venda = repository.getReferenceById(vendaId);
//
//        if(venda.getStatusVenda() == StatusVenda.PENDENTE){
//            if(venda.getItens().size() > 0){
//                Instant dataPgto = Instant.now();
//                Pagamento pagamento = pagamentoService.criar(venda, dataPgto);
//                venda.setPagamento(pagamento);
//                venda.setStatusVenda(StatusVenda.FECHADA);
//                return repository.save(venda);
//            } else{
//        throw new EmptyOrderExeption(vendaId);
//    }else {
//            throw new UnavailableOrderException(vendaId);
//        }
//    }
//
//
//    //gerar rel mensal
//
//    public RelatorioDTO relatorioMensal (Integer mes, Integer ano){
//        YearMonth mesRelat = YearMonth.of(ano, mes);
//
//        LocalDateTime dataInicialLocal = mesRelat.atDay(1).atStartOfDay();
//        LocalDateTime dataFinalLocal = mesRelat.atEndOfMonth().atTime().atTime(23,59,59);
//
//        Instant dataInicial = dataInicialLocal.toInstant(ZoneOffset.UTC);
//        Instant dataFinal = dataFinalLocal.toInstant(ZoneOffset.UTC);
//
//        Relatorio dto = gerarRelatorio(dataInicial, dataFinal);
//
//        return dto;
//    }
//
//    //gerar rel semanal
//    public RelatorioDTO relatorioSemanal (Integer ano, Integer mes, Integer dia){
//        YearMonth mesRelat = YearMonth.of(ano, mes);
//
//        LocalDateTime dataConsulta = localDateTime.of(ano, mes, dia, 0, 0);
//
//        DayOfWeek diaDaSemana = dataConsulta.getDayOfWeek();
//
//        System.out.println(diaDaSemana);
//


