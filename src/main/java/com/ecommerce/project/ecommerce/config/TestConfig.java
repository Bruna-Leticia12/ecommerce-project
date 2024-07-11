package com.ecommerce.project.ecommerce.config;

import com.ecommerce.project.ecommerce.entities.*;
import com.ecommerce.project.ecommerce.enums.SaleStatus;
import com.ecommerce.project.ecommerce.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final SaleItemRepository saleItemRepository;

    private UserRepository userRepository;

    private OrderRepository orderRepository;

    private CategoryRepository categoryRepository;

    private ProductRepository productRepository;

    private SaleItem saleItem;

    public TestConfig(SaleItemRepository saleItemRepository) {
        this.saleItemRepository = saleItemRepository;
    }

    @Override
    public void run (String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5,"");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.",1250.0,  "");
        Product p4 = new Product(null, "PC Gamer",  "Donec aliquet odio ac rhoncus cursus.",1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies",  "Cras fringilla convallis sem vel faucibus.",100.99, "");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        User u1 = new User(null, "Joao Paulo", "joao@gmail.com", "98654571", "369852");
        User u2 = new User(null, "Marta Souza", "marta@gmail.com", "94127326", "825417");

        Order o1 = new Order(null, Instant.parse("2024-07-03T19:53:07Z"), SaleStatus.CONFIRMED, u1);
        Order o2 = new Order(null, Instant.parse("2024-07-05T03:42:10Z"), SaleStatus.SHIPPED, u2);
        Order o3 = new Order(null, Instant.parse("2024-07-06T15:21:22Z"), SaleStatus.CANCELED, u1);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        SaleItem oi1 = new SaleItem(o1, p1, 2, p1.getPrice());
        SaleItem oi2 = new SaleItem(o1, p3, 1, p3.getPrice());
        SaleItem oi3 = new SaleItem(o2, p3, 2, p3.getPrice());
        SaleItem oi4 = new SaleItem(o3, p5, 2, p5.getPrice());

        saleItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Sale sale1 = new Sale(null,Instant.parse("2024-07-03T21:53:07Z"), o1);
        o1.setSale(sale1);

        orderRepository.save(o1);
    }
}
