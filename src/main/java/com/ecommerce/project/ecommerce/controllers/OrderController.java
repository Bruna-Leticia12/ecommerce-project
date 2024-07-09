package com.ecommerce.project.ecommerce.controllers;

import com.ecommerce.project.ecommerce.dto.OrderDto;
import com.ecommerce.project.ecommerce.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class OrderController {


    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public OrderDto inserirPedido (@RequestBody OrderDto orderDto){

        OrderDto response = orderService.createOrder(orderDto);

        return orderDto;
    }

}
