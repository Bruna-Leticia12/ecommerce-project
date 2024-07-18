package com.ecommerce.project.ecommerce.repositories;

import com.ecommerce.project.ecommerce.entities.SaleItem;
import com.ecommerce.project.ecommerce.entities.pk.SaleItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, SaleItemPK> {

}
