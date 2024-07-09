package com.ecommerce.project.ecommerce.repositories;

import com.ecommerce.project.ecommerce.entities.SaleItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItemEntity, Integer> {
}
