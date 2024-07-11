package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Order;
import com.ecommerce.project.ecommerce.repositories.OrderRepository;
import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }

    @Transactional
    public Order insert(Order obj) {
        return orderRepository.save(obj);
    }

    @Transactional
    public void delete(Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public Order update(Long id, Order obj) {
        try {
            Order entity = orderRepository.getOne(id);
            updateData(entity, obj);
            return orderRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Order entity, Order obj) {
        entity.setShippingDate(obj.getShippingDate());
        entity.setSaleStatus(obj.getSaleStatus());
    }
}
