package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Product;
import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }

}
//
//    @Transactional(readOnly = true)
//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }
//
//    @Transactional(readOnly = true)
//    public Product findById(Long id) {
//        return productRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
//    }
//
//    @Transactional
//    public Product insert(Product obj) {
//        return productRepository.save(obj);
//    }
//
//    @Transactional
//    public void delete(Long id) {
//        Product product = findById(id);
//        if (!product.getOrder().isEmpty()) {
//            throw new RuntimeException("Cannot delete product associated with orders");
//        }
//        product.setActive(false);
//        productRepository.save(product);
//    }
//
//    @Transactional
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
//
//    private void updateData(Product entity, Product obj) {
//        entity.setName(obj.getName());
//        entity.setDescription(obj.getDescription());
//        entity.setPrice(obj.getPrice());
//        entity.setStockQuantity(obj.getStockQuantity());
//    }
//}