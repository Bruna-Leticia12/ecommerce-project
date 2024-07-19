package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Product;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.InsufficientStockException;
import com.ecommerce.project.ecommerce.services.exceptions.MethodNotAllowed;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "products")
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    //Listar todos os produtos
    @Cacheable(key = "#root.methodName")
    public List<Product> findAll() {
        return repository.findAll();
    }

    //Listar o produto por id
    public Product findById(Integer id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    //Listar os todos os produtos Ativos
    @CacheEvict(allEntries = true)
    public List<Product> findAvailable(){
        return repository.findByAvailableTrue();
    }

    //Listar os todos os produtos Inativos
    public List<Product> findInactive(){
        return repository.findByAvailableFalse();
    }

    //Inserir um produto
    @CacheEvict(allEntries = true)
    public Product insert(Product obj) {
        validateProductPrice(obj.getPrice());
        return repository.save(obj);
    }

    //Deletar um produto
    @CacheEvict(allEntries = true)
    public void delete(Integer id) {
        Product product = findById(id);
        if (!product.getSales().isEmpty()) {
            throw new DatabaseException("Cannot delete product that has been sold");
        }
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    //Atualizar um produto
    @CacheEvict(allEntries = true)
    public Product update(Integer id, Product obj) {
        try {
            validateProductPrice(obj.getPrice());
            Product entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    //Atualizar dados do produto
    private void updateData(Product entity, Product obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());
        entity.setStockQuantity(obj.getStockQuantity());
    }

    //Retirar produtos do estoque
    @CacheEvict(allEntries = true)
    public Product removeStockItem(Integer id, Integer quantity) {
        try {
            Product entity = repository.getReferenceById(id);
            if (entity.getStockQuantity() >= quantity) {
                entity.setStockQuantity(entity.getStockQuantity() - quantity);
                return repository.save(entity);
            }
            else {
                throw new InsufficientStockException("Insufficient stock for product");
            }
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    //Acresentar produtos no estoque
    @CacheEvict(allEntries = true)
    public Product includeStockItem(Integer id, Integer qtde) {
        try {
            Product entity = repository.getReferenceById(id);
            entity.setStockQuantity(entity.getStockQuantity() + qtde);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // Deixar um produto inativo
    @CacheEvict(allEntries = true)
    public Product inactiveProduct(Integer id) {
        try {
            Product entity = repository.getReferenceById(id);
            entity.setAvailable(false);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    // Método para validar se o preço do produto é positivo
    private void validateProductPrice(double price) {
        if (price <= 0) {
            throw new MethodNotAllowed("Price must be positive");
        }
    }
}
