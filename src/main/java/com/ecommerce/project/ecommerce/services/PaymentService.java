package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Payment;
import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.repositories.PaymentRepository;
import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    // Para consultar pagamento por id
    public List<Payment> findAll() {
        return repository.findAll();

    }

    // Para listar todos pagamento
    public Payment findById(Integer id) {
        Optional<Payment> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }


    // Para criar um novo pagamento
    public Payment create(Sale sale, Instant paymentDate) {
        Payment payment = new Payment();
        payment.setPaymentDate(paymentDate);
        payment.setSale(sale);
        return repository.save(payment);
    }

    // Para deletar um pagamento
    public void delete(Integer id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new ResourceNotFoundException(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}

