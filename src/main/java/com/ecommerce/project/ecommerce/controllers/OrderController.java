package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.entities.Order;
import com.ecommerce.project.ecommerce.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = orderService.findALL();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        Order obj = orderService.findById(id);
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
