package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import org.springframework.stereotype.Service;
import com.ecommerce.project.ecommerce.entities.User;

import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private SaleRepository repository;

    public SaleService(SaleRepository repository) {
        this.repository = repository;
    }

    public List<Sale> findAll() {
        return repository.findAll();
    }

    public Sale findById(Long id) {
        Optional<Sale> obj = repository.findById(id);
        return obj.get();
    }

//    @Transactional(readOnly = true)
//
//
//
//
//    @Transactional(readOnly = true)
//    public Sale findById(Long id) {
//        Optional<Sale> obj = repository.findById(id);
//        return obj.get();
////        return repository.findById(id)
////                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
//    }


//
//    @Transactional
//    public Sale insert(Sale obj) {
//        return saleRepository.save(obj);
//    }
//
//    @Transactional
//    public void delete(Long id) {
//        try {
//            saleRepository.deleteById(id);
//        } catch (EmptyResultDataAccessException e) {
//            throw new ResourceNotFoundException(id);
//        } catch (DataIntegrityViolationException e) {
//            throw new DatabaseException(e.getMessage());
//        }
//    }
//
//    @Transactional
//    public Sale update(Long id, Sale obj) {
//        try {
//            Sale entity = saleRepository.getOne(id);
//            updateData(entity, obj);
//            return saleRepository.save(entity);
//        } catch (EntityNotFoundException e) {
//            throw new ResourceNotFoundException(id);
//        }
//    }
//
//    private void updateData(Sale entity, Sale obj) {
//        entity.setSaleDate(obj.getSaleDate());
//        entity.setSaleStatus(obj.getSaleStatus());
//    }
}
