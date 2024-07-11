package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Order;
import com.ecommerce.project.ecommerce.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public List<Order> findALL(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }



//    public OrderService(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//    public OrderDto createOrder(OrderDto orderDto){
//
//        OrderEntity orderEntity = new OrderEntity();
//
//        orderEntity.setId_cliente(orderDto.getId_cliente());
//        orderEntity.setValor_total(orderDto.getValor_total());
//        orderEntity.setEnviado(orderDto.isEnviado());
//        orderEntity.setData(orderDto.getData());
//
//        orderRepository.save(orderEntity);
//
//        return orderDto;
//    }
}
