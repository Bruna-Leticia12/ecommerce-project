package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.SaleDto;
import com.ecommerce.project.ecommerce.services.SaleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
public class SaleController {

    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public SaleDto inserirVenda (@RequestBody SaleDto saleDto){

        SaleDto response = saleService.createSale(saleDto);

        return saleDto;
    }
}
