package com.ecommerce.project.ecommerce.repositories;

import com.ecommerce.project.ecommerce.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;

@Repository
public interface SaleRepository  extends JpaRepository<Sale, Integer> {

    //List<Sale> findBySaleDateBetween(Instant initialDate, Instant finalDate);

    //List<Sale> findBySaleDateBetween(Instant startDate, Instant endDate);
    List<Sale> findBySaleDateBetween(Instant initialDate, Instant finalDate);

}
