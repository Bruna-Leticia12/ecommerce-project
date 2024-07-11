package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        return sale.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Sale insert(Sale obj){
        return saleRepository.save(obj);
    }

    public void delete(Long id){
        try {
            saleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Sale update(Long id, Sale obj){
        try {
            Sale entity = saleRepository.getReferenceById(id);
            updateData(entity, obj);
            return saleRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Sale entity, Sale obj) {
        entity.setSaleDateUpdate(obj.getSaleDate());
        entity.setSaleStatus(obj.getSaleStatus());
    }
}
