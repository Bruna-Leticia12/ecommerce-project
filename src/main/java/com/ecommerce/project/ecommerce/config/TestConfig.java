package com.ecommerce.project.ecommerce.config;

import com.ecommerce.project.ecommerce.entities.Product;
import com.ecommerce.project.ecommerce.entities.Sale;
import com.ecommerce.project.ecommerce.entities.User;
import com.ecommerce.project.ecommerce.enums.SaleStatus;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Product p1 = new Product(null, "Notebook", "Dell, Intel Core i7", 4000.00, 8);
        Product p2 = new Product(null, "Calular", "Samsung, Galaxy S22", 2980.99, 15);
        Product p3 = new Product(null, "TV", "LG 55 polegadas, 4K UHD", 2799.90, 10);
        Product p4 = new Product(null, "Fone de Ouvido", "Bluetooth JBL", 598.99, 20);
        Product p5 = new Product(null, "Playstation 5", "Console com 1TB", 4699.99, 4);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        User u1 = new User(null, "Carmelia Souza", "carmelia@gmail.com", "995624713", "147369");
        User u2 = new User(null, "Renato Ferreira", "renato@gmail.com", "993268485", "852617");

        userRepository.saveAll(Arrays.asList(u1, u2));

        Sale s1 = new Sale(null, Instant.parse("2024-07-16T10:53:07Z"), SaleStatus.CONFIRMED, u1);
        Sale s2 = new Sale(null, Instant.parse("2024-07-16T03:42:10Z"), SaleStatus.WATING_PAYMENT, u2);
        Sale s3 = new Sale(null, Instant.parse("2024-07-16T15:21:22Z"), SaleStatus.WATING_PAYMENT,  u1);

        saleRepository.saveAll(Arrays.asList(s1, s2, s3));
    }

}
