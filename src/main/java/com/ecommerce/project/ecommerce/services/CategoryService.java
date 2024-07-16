//package com.ecommerce.project.ecommerce.services;
//
//import com.ecommerce.project.ecommerce.entities.Category;
//import com.ecommerce.project.ecommerce.repositories.CategoryRepository;
//import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CategoryService {

//    private CategoryRepository categoryRepository;
//
//    public CategoryService(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Category> findAll() {
//        return categoryRepository.findAll();
//    }
//
//    @Transactional(readOnly = true)
//    public Category findById(Long id) {
//        return categoryRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
//    }
//
//    @Transactional
//    public Category insert(Category obj) {
//        return categoryRepository.save(obj);
//    }
//
//    @Transactional
//    public Category update(Long id, Category obj) {
//        try {
//            Category entity = categoryRepository.getOne(id);
//            updateData(entity, obj);
//            return categoryRepository.save(entity);
//        } catch (EntityNotFoundException e) {
//            throw new ResourceNotFoundException(id);
//        }
//    }
//
//    private void updateData(Category entity, Category obj) {
//        entity.setName(obj.getName());
//    }
//}
