package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.SaleItemDTO;
import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    private SaleService service;

    @Autowired
    public SaleController(SaleService service) {
        this.service = service;
    }

    //Listar todas as vendas
    @GetMapping
    public ResponseEntity<List<Sale>> findAll() {
        List<Sale> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Listar uma venda por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Sale> findById(@PathVariable Integer id) {
        Sale obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //Inserir uma venda
    @PostMapping(value = "/create-sale")
    public ResponseEntity<Sale> create(@RequestParam (value = "user-id") Integer userId) {
        Sale sale = service.create(userId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sale.getId()).toUri();
        return ResponseEntity.created(uri).body(sale);
    }

    //Inserir um item na venda
    @PostMapping(value="{saleId}/insert-item")
    public ResponseEntity<Sale> insertItem(@PathVariable Integer saleId, @RequestBody SaleItemDTO obj) {
        Sale sale = service.insertItem(saleId, obj);
        return ResponseEntity.ok().body(sale);
    }

    //Remover um item da venda
    @DeleteMapping(value = "{saleId}/remove-item/{productId}")
    public ResponseEntity<Sale> itemRemove(@PathVariable Integer saleId, @PathVariable Integer productId) {
        Sale sale = service.itemRemove(saleId, productId);
        return ResponseEntity.ok().body(sale);
    }

    //Atualizar quantidade venda
    @PutMapping(value = "{saleId}/update-quantity")
    public ResponseEntity<Sale> updateQuantity(@PathVariable Integer saleId, @RequestBody SaleItemDTO dto) {
        System.out.println(dto);
        Sale sale = service.updateQuantity(saleId, dto);
        return ResponseEntity.ok().body(sale);
    }

    // Confirmar uma venda
    @PutMapping("/{id}/confirm-sale")
    public ResponseEntity<Sale> confirmSale(@PathVariable Integer id) {
        Sale sale = service.confirmSale(id);
        return ResponseEntity.ok().body(sale);
    }

    //Cancelar uma venda
    @PutMapping("/{id}/cancel-sale")
    public ResponseEntity<Sale> cancelSale(@PathVariable Integer id) {
        Sale sale = service.cancelSale(id);
        return ResponseEntity.ok().body(sale);
    }
}

//    //Listar venda por data
//    @PostMapping(value = "/query-date-sale")
//    public ResponseEntity<List<Sale>> queryDate(@RequestBody QueryDateDTO dto) {
//        List<Sale> list = service.queryDate(dto);
//        return ResponseEntity.ok().body(list);
//    }