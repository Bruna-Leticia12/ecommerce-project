package com.ecommerce.project.ecommerce.config;

import com.ecommerce.project.ecommerce.entities.*;
import com.ecommerce.project.ecommerce.enums.SaleStatus;
import com.ecommerce.project.ecommerce.enums.UserRole;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import com.ecommerce.project.ecommerce.repositories.SaleItemRepository;
import com.ecommerce.project.ecommerce.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Usuários
        User u1 = new User("Cassia Souza", "cassia@gmail.com", "995624713", passwordEncoder.encode("147369"), UserRole.USER);
        User u2 = new User("Renato Ferreira", "renato@gmail.com", "993268485", passwordEncoder.encode("852617"), UserRole.ADMIN);
        User u3 = new User("Manuel Queiroz", "manuel@gmail.com", "99426581", passwordEncoder.encode("647892"), UserRole.USER);
        User u4 = new User("Selma Martins", "semal@gmail.com", "991254622", passwordEncoder.encode("268745"), UserRole.USER);
        User u5 = new User("Julia Chagas", "julia@gmail.com", "994705511", passwordEncoder.encode("463587"), UserRole.USER);

        // Vendas
        Sale s1 = new Sale(null, Instant.parse("2024-07-02T10:53:07Z"), SaleStatus.WAITING_PAYMENT, u1);
        Sale s2 = new Sale(null, Instant.parse("2024-07-08T03:42:10Z"), SaleStatus.CONFIRMED, u2);
        Sale s3 = new Sale(null, Instant.parse("2024-07-10T14:21:22Z"), SaleStatus.WAITING_PAYMENT, u1);
        Sale s4 = new Sale(null, Instant.parse("2024-07-14T09:15:08Z"), SaleStatus.CONFIRMED, u2);
        Sale s5 = new Sale(null, Instant.parse("2024-07-16T17:30:47Z"), SaleStatus.CANCELED, u3);
        Sale s6 = new Sale(null, Instant.parse("2024-07-17T11:10:54Z"), SaleStatus.CONFIRMED, u4);
        Sale s7 = new Sale(null, Instant.parse("2024-07-18T14:44:19Z"), SaleStatus.CONFIRMED, u5);

        saleRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7));

        // Produtos
        Product p1 = new Product(null, "Notebook", "Dell", 4000.00, 8, true);
        Product p2 = new Product(null, "Calular", "Samsung", 2980.99, 15, true);
        Product p3 = new Product(null, "TV", "LG", 2799.90, 10, true);
        Product p4 = new Product(null, "Fone de Ouvido", "JBL", 598.99, 20, true);
        Product p5 = new Product(null, "Impressora", "HP", 4699.99, 4, false);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        // Criando e salvando itens de venda
        SaleItem si1 = new SaleItem(s1, p1, 1, p1.getPrice());
        SaleItem si2 = new SaleItem(s2, p3, 3, p3.getPrice());
        SaleItem si3 = new SaleItem(s3, p2, 2, p2.getPrice());
        SaleItem si4 = new SaleItem(s4, p5, 1, p5.getPrice());
        SaleItem si5 = new SaleItem(s5, p4, 5, p4.getPrice());
        SaleItem si6 = new SaleItem(s6, p2, 1, p2.getPrice());
        SaleItem si7 = new SaleItem(s6, p1, 2, p1.getPrice());

        saleItemRepository.saveAll(Arrays.asList(si1, si2, si3, si4, si5, si6, si7));

        // Pagamento
        Payment pay1 = new Payment(null, Instant.parse("2024-07-08T04:42:10Z"), s2);
        Payment pay2 = new Payment(null, Instant.parse("2024-07-14T10:15:08Z"), s4);
        Payment pay3 = new Payment(null, Instant.parse("2024-07-17T12:10:54Z"), s6);
        Payment pay4 = new Payment(null, Instant.parse("2024-07-18T15:44:19Z"), s7);
        s2.setPayment(pay1);
        s4.setPayment(pay2);
        s6.setPayment(pay3);
        s7.setPayment(pay4);

        saleRepository.saveAll(Arrays.asList(s1, s2, s3, s4));
    }
}
