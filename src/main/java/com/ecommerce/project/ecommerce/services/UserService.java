package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.entities.User;
import com.ecommerce.project.ecommerce.repositories.UserRepository;
import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

//    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }


    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.get();
    }

//    @Transactional(readOnly = true)
//    public User findById(Long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
//    }
//
//    @Transactional
//    public User insert(User obj) {
//        return userRepository.save(obj);
//    }
//
//    @Transactional
//    public void delete(Long id) {
//        try {
//            userRepository.deleteById(id);
//        } catch (EmptyResultDataAccessException e) {
//            throw new ResourceNotFoundException(id);
//        } catch (DataIntegrityViolationException e) {
//            throw new DatabaseException(e.getMessage());
//        }
//    }
//
//    @Transactional
//    public User update(Long id, User obj) {
//        try {
//            User entity = userRepository.getOne(id);
//            updateData(entity, obj);
//            return userRepository.save(entity);
//        } catch (EntityNotFoundException e) {
//            throw new ResourceNotFoundException(id);
//        }
//    }
//
//    private void updateData(User entity, User obj) {
//        entity.setName(obj.getName());
//        entity.setEmail(obj.getEmail());
//        entity.setPhone(obj.getPhone());
//    }
}
