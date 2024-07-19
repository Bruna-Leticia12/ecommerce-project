package com.ecommerce.project.ecommerce.services;

import com.ecommerce.project.ecommerce.dto.UserUpdateDTO;
import com.ecommerce.project.ecommerce.entities.User;
import com.ecommerce.project.ecommerce.repositories.UserRepository;
import com.ecommerce.project.ecommerce.services.exceptions.DatabaseException;
import com.ecommerce.project.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "users")
public class UserService {

    @Autowired
    private UserRepository repository;

    //Listar todos os usuarios
     @Cacheable(key="#root.methodName")
    public List<User> findAll() {
        return repository.findAll();
    }

    //Listar o usuario por id
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //Inserir um usuario
    @CacheEvict(allEntries = true)
    public User insert(User obj) {
        return repository.save(obj);
    }

    //Deletar um usuario
    @CacheEvict(allEntries = true)
    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    //Atualizar um usuario
    @CacheEvict(allEntries = true)
    public User update(Integer id, UserUpdateDTO dto) {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, dto);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    //Atualizar dados do usuario
    private void updateData(User entity, UserUpdateDTO dto) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
    }
}
