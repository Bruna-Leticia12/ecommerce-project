package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.ProductDto;
import com.ecommerce.project.ecommerce.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDto inserirProduto (@RequestBody ProductDto productDto){

        ProductDto response = productService.createProduct(productDto);

        return productDto;
    }





}
