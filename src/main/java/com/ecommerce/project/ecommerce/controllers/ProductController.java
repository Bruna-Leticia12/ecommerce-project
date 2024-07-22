package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.entities.Product;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import com.ecommerce.project.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @Autowired
    private ProductService service;

    //Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Listar o produto por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //Listar os todos os produtos Ativos
    @GetMapping(value = "/available")
    public ResponseEntity<List<Product>> findAvailable() {
        List<Product> list = service.findAvailable();
        return ResponseEntity.ok().body(list);
    }

    //Listar os todos os produtos Inativos
    @GetMapping(value = "/inactive")
    public ResponseEntity<List<Product>> findInactive() {
        List<Product> list = service.findInactive();
        return ResponseEntity.ok().body(list);
    }

    //Inserir um produto
    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    //Deletar um produto
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Atualizar um produto
    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    // Deixar um produto inativo
    @PutMapping(value = "/{id}/inactive-product")
    public ResponseEntity<Product> inactive(@PathVariable Integer id) {
        Product product = service.inactiveProduct(id);
        return ResponseEntity.ok().body(product);
    }
}
