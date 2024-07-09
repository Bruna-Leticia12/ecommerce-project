package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.OrderDto;
import com.ecommerce.project.ecommerce.entities.OrderEntity;
import com.ecommerce.project.ecommerce.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto createOrder(OrderDto orderDto){

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId_cliente(orderDto.getId_cliente());
        orderEntity.setValor_total(orderDto.getValor_total());
        orderEntity.setEnviado(orderDto.isEnviado());
        orderEntity.setData(orderDto.getData());

        orderRepository.save(orderEntity);

        return orderDto;
    }
}
