package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Product;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
//@CacheConfig(cacheNames = "products")
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    //    @Cacheable(key = "#root.methodName")
    public List<Product> findAll() {
        return repository.findAll();
    }

//    public Product findById(Long id) {
//        Optional<Product> obj = repository.findById(id);
//        return obj.get();
//    }

    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    //consultar produtos ativos
    //    @Cacheable(key = "#root.methodName")
    public List<Product> findAvailable(){
        return repository.findByAvailableTrue();
    }

    public List<Product> findInactive(){
        return repository.findByAvailableFalse();
    }

    public Product insert(Product obj) {

        return repository.save(obj);
    }


        //@CacheEvict(value = "products", allEntries = true)
//        public void delete(Long id) {
//            try {
//                if (repository.existsById(id)) {
//                    repository.deleteById(id);
//                }
//                else {
//                    throw new ResourceNotFoundException(id);
//                }
//                } catch (DataIntegrityViolationException e) {
//                throw new DatabaseException(e.getMessage());
//            }
//        }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    //    public void delete(Long id) {
//        Product product = findById(id);
//        if (!product.getOrder().isEmpty()) {
//            throw new RuntimeException("Cannot delete product associated with orders");
//        }
//        product.setActive(false);
//        productRepository.save(product);
//    }

    //    public Product update(Long id, Product obj) {
//        Product entity = findById(id);
//
//        if (!entity.isActive()) {
//            throw new RuntimeException("Inactive product cannot be updated");
//        }
//
//        updateData(entity, obj);
//
//        return productRepository.save(entity);
//    }

    public Product update(Long id, Product obj) {
        try {
            Product entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Product entity, Product obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());
        entity.setStockQuantity(obj.getStockQuantity());
    }
}
