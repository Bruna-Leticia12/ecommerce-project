package com.ecommerce.project.ecommerce.config;

import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.entities.User;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import com.ecommerce.project.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SaleRepository saleRepository;


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

        Sale s1 = new Sale(null, Instant.parse("2024-07-16T10:53:07Z"), u1);
        Sale s2 = new Sale(null, Instant.parse("2024-07-16T03:42:10Z"), u2);
        Sale s3 = new Sale(null, Instant.parse("2024-07-16T15:21:22Z"), u1);

        saleRepository.saveAll(Arrays.asList(s1, s2, s3));
    }

}
