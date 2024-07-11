package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.entities.Product;
import com.ecommerce.project.ecommerce.entities.User;
import com.ecommerce.project.ecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

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


    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product obj){
        obj = productService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product obj){
        obj = productService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }


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
