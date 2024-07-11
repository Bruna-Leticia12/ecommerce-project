package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.Category;
import com.ecommerce.project.ecommerce.repositories.CategoryRepository;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findALL(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.get();
    }

    public Category insert(Category obj){
        return categoryRepository.save(obj);
    }

    public Category update(Long id, Category obj){
        try {
            Category entity = categoryRepository.getReferenceById(id);
            updateData(entity, obj);
            return categoryRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Category entity, Category obj) {
        entity.setName(obj.getName());
    }
}
