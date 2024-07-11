package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.entities.Category;
import com.ecommerce.project.ecommerce.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {


    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> list = categoryService.findALL();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category obj = categoryService.findById(id);
        return ResponseEntity.ok().body(obj);
    }


//    public OrderController(OrderService orderService){
//        this.orderService = orderService;
//    }




//    @PostMapping
//    public OrderDto inserirPedido (@RequestBody OrderDto orderDto){
//
//        OrderDto response = orderService.createOrder(orderDto);
//
//        return orderDto;
//    }

}
