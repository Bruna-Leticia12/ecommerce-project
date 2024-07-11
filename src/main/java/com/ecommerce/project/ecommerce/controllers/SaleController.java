package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.ProductDto;
import com.ecommerce.project.ecommerce.dto.SaleDto;
import com.ecommerce.project.ecommerce.dto.request.ProductRequest;
import com.ecommerce.project.ecommerce.dto.request.SaleRequest;
import com.ecommerce.project.ecommerce.services.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class SaleController {

//    private SaleService saleService;
//
//    public SaleController(SaleService saleService) {
//        this.saleService = saleService;
//    }
//
//    @PostMapping
//    public SaleDto inserirVenda (@RequestBody SaleRequest saleRequest){
//
//        SaleDto response = saleService.createSale(saleRequest);
//
//        return response;
//    }
//
//    @GetMapping
//    public List<SaleDto> listarVendas(){
//
//        List<SaleDto> response = saleService.getAllSales();
//
//        return response;
//    }
//
//    @GetMapping("/id")
//    public SaleDto getPorId(@RequestParam Integer id) {
//
//        SaleDto response = saleService.findById(id);
//
//        return response;
//    }
//
//    @PatchMapping("/confirmacao")
//    public String confirmarVenda (@RequestParam Integer id) {
//
//        String response = saleService.confirmSale(id);
//
//        return response;
//    }
//
//    @PatchMapping("/cancelamento")
//    public String cancelarVenda (@RequestParam Integer id) {
//
//        String response = saleService.cancelSale(id);
//
//        return response;
//    }
//
//    @DeleteMapping
//    public void deletarVenda (@RequestParam Integer id){
//        saleService.deleteById(id);
//    }
//

}
