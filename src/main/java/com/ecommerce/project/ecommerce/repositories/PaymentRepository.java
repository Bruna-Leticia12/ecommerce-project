package com.ecommerce.project.ecommerce.repositories;

import com.ecommerce.project.ecommerce.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
