package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.SaleDto;
import com.ecommerce.project.ecommerce.dto.request.SaleRequest;
import com.ecommerce.project.ecommerce.services.SaleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venda")
public class SaleController {

    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public SaleDto inserirVenda (@RequestBody SaleRequest saleRequest){

        SaleDto response = saleService.createSale(saleRequest);

        return response;
    }


//    @GetMapping
//    public List<SaleDto> listarVendas(){
//
//        List<SaleDto> response = saleService.getSale();
//
//        return response;
//    }

}
