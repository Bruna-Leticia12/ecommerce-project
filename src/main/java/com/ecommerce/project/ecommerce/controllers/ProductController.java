package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.ProductDto;
import com.ecommerce.project.ecommerce.dto.request.ProductRequest;
import com.ecommerce.project.ecommerce.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<ProductDto> listarProdutos(){

        List<ProductDto> response = productService.getProducts();

        return response;
    }

    @GetMapping("/id")
    public ProductDto getPorId(@RequestParam Integer id){

        ProductDto response = productService.findById(id);

        return response;
    }

    @PutMapping
    public ProductDto atualizarProduto (@RequestParam Integer id, @RequestBody ProductRequest productRequest) {

        ProductDto response = productService.updateById(id, productRequest);

        return response;
    }

    @DeleteMapping
    public void deletarProduto (@RequestParam Integer id){
        productService.deleteById(id);
    }

}
