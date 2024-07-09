package com.ecommerce.project.ecommerce.repositories;

import com.ecommerce.project.ecommerce.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends JpaRepository<OrderEntity, Integer> {
}
