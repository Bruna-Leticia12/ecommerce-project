package com.ecommerce.project.ecommerce.repositories;

import com.ecommerce.project.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    UserDetails findByLogin(String login);

//    Optional<User> findByEmail(String email);
//
}


//public interface UserRepository extends JpaRepository<User, Integer> {
