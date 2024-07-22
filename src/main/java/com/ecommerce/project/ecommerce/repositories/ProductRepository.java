package com.ecommerce.project.ecommerce.repositories;

import com.ecommerce.project.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByAvailableTrue();

    List<Product> findByAvailableFalse();

}
