//package com.ecommerce.project.ecommerce.infra.security;
//
//
//import com.ecommerce.project.ecommerce.entities.User;
//import com.ecommerce.project.ecommerce.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//
//@Component
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepository repository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = this.repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new ArrayList<>());
//    }
//}