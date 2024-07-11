package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.services.SaleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/venda")
public class SaleController {

    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> findAll(){
        List<Sale> list = saleService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sale> findById(@PathVariable Long id){
        Sale obj = saleService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Sale> insert(@RequestBody Sale obj){
        obj = saleService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Sale> update(@PathVariable Long id, @RequestBody Sale obj){
        obj = saleService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/data")
    public ResponseEntity<List<Sale>> findBySaleDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Instant startInstant = startDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Instant endInstant = endDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        List<Sale> list = saleService.findBySaleDateBetween(startInstant, endInstant);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/relatorio-mensal")
    public ResponseEntity<List<Sale>> generateMonthlySalesReport() {
        List<Sale> monthlySales = saleService.generateMonthlySalesReport();
        return ResponseEntity.ok().body(monthlySales);
    }

    @GetMapping("/relatorio-semanal")
    public ResponseEntity<List<Sale>> generateWeeklySalesReport() {
        List<Sale> weeklySales = saleService.generateWeeklySalesReport();
        return ResponseEntity.ok().body(weeklySales);
    }
}
