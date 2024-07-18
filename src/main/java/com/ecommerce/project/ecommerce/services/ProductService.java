package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Product;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.InsufficientStockException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
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

    //Listar todos os produtos
    //    @Cacheable(key = "#root.methodName")
    public List<Product> findAll() {
        return repository.findAll();
    }

    //Listar o produto por id
    public Product findById(Integer id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    //Listar os todos os produtos Ativos
    //    @Cacheable(key = "#root.methodName")
    public List<Product> findAvailable(){

        return repository.findByAvailableTrue();
    }

    // Deixar um produto inativo
    //@CacheEvict(allEntries = true)
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

    //Listar os todos os produtos Inativos
    public List<Product> findInactive(){
        return repository.findByAvailableFalse();
    }

    //Inserir um produto
    //@CacheEvict(allEntries = true)
    public Product insert(Product obj) {
        return repository.save(obj);
    }

    //Deletar um produto
    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    //Atualizar um produto
    public Product update(Integer id, Product obj) {
        try {
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
                throw new InsufficientStockException(id);
            }

        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    //Acresentar produtos no estoque
    // @CacheEvict(allEntries = true)
    public Product includeStockItem(Integer id, Integer qtde) {
        try {
            System.out.println("2");
            Product entity = repository.getReferenceById(id);
            System.out.println("estoque=" + entity.getStockQuantity());
            System.out.println("qtde=" + qtde);
            entity.setStockQuantity(entity.getStockQuantity() + qtde);
            System.out.println("estoque=" + entity.getStockQuantity());
            System.out.println("qtde=" + qtde);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

}
