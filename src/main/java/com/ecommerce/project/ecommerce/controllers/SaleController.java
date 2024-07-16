package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.services.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    private SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> findAll(){
        List<Sale> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sale> findById(@PathVariable Long id){
        Sale obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
//
//    @PostMapping
//    public ResponseEntity<Sale> insert(@RequestBody Sale obj){
//        obj = service.insert(obj);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//        return ResponseEntity.created(uri).body(obj);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id){
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Sale> update(@PathVariable Long id, @RequestBody Sale obj){
//        obj = service.update(id, obj);
//        return ResponseEntity.ok().body(obj);
//    }
}
