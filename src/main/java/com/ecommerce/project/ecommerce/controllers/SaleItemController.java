package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.SaleItemDto;
import com.ecommerce.project.ecommerce.services.SaleItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itensVenda")
public class SaleItemController {

    private SaleItemService saleItemService;

    public SaleItemController(SaleItemService saleItemService) {
        this.saleItemService = saleItemService;
    }

    @PostMapping
    public SaleItemDto inserirItemVenda (@RequestBody SaleItemDto saleItemDto){

        SaleItemDto response = saleItemService.createSaleItem(saleItemDto);

        return saleItemDto;
    }

}
