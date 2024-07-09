package com.ecommerce.project.ecommerce.repositories;

import com.ecommerce.project.ecommerce.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Integer> {
}
