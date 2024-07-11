package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.entities.Product;
import com.ecommerce.project.ecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = productService.findALL();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product obj = productService.findById(id);
        return ResponseEntity.ok().body(obj);
    }


//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @PostMapping
//    public ProductDto inserirProduto (@RequestBody ProductDto productDto){
//
//        ProductDto response = productService.createProduct(productDto);
//
//        return productDto;
//    }
//
//    @GetMapping
//    public List<ProductDto> listarProdutos(){
//
//        List<ProductDto> response = productService.getProducts();
//
//        return response;
//    }
//
//    @GetMapping("/id")
//    public ProductDto getPorId(@RequestParam Integer id){
//
//        ProductDto response = productService.findById(id);
//
//        return response;
//    }
//
//    @PutMapping
//    public ProductDto atualizarProduto (@RequestParam Integer id, @RequestBody ProductRequest productRequest) {
//
//        ProductDto response = productService.updateById(id, productRequest);
//
//        return response;
//    }
//
//    @DeleteMapping
//    public void deletarProduto (@RequestParam Integer id){
//        productService.deleteById(id);
//    }

}
