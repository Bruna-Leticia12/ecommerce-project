package com.ecommerce.project.ecommerce.config;

import com.ecommerce.project.ecommerce.entities.User;
import com.ecommerce.project.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;


//    private SaleRepository saleRepository;


//    public TestConfig() {}
//
//    public TestConfig(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    public TestConfig(SaleRepository saleRepository) {
//        this.saleRepository = saleRepository;
//    }
//
//    public TestConfig(UserRepository userRepository, SaleRepository saleRepository) {
//        this.userRepository = userRepository;
//        this.saleRepository = saleRepository;
//    }

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Carmelia Souza", "carmelia@gmail.com", "995624713", "147369");
        User u2 = new User(null, "Renato Ferreira", "renato@gmail.com", "993268485", "852617");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }

}
