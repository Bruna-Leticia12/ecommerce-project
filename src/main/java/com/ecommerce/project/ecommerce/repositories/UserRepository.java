package com.ecommerce.project.ecommerce.repositories;

import com.ecommerce.project.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}


//public interface UserRepository extends JpaRepository<User, Integer> {
