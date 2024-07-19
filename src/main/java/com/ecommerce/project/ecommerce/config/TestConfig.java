package com.ecommerce.project.ecommerce.config;

import com.ecommerce.project.ecommerce.entities.*;
import com.ecommerce.project.ecommerce.enums.SaleStatus;
import com.ecommerce.project.ecommerce.repositories.ProductRepository;
import com.ecommerce.project.ecommerce.repositories.SaleItemRepository;
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

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Override
    public void run(String... args) throws Exception {

        // Criando e salvando usuários
        User u1 = new User( "Cassia Souza", "cassia@gmail.com", "995624713", "147369");
        User u2 = new User( "Renato Ferreira", "renato@gmail.com", "993268485", "852617");
        User u3 = new User( "Manuel Queiroz", "manuel@gmail.com", "99426581", "647892");
        User u4 = new User( "Selma Martins", "semal@gmail.com", "991254622", "268745");
        User u5 = new User( "Julia Chagas", "julia@gmail.com", "994705511", "463587");

        //userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, u5));

        // Criando e salvando vendas
        Sale s1 = new Sale(null, Instant.parse("2024-07-13T10:53:07Z"), SaleStatus.WAITING_PAYMENT, u1);
        Sale s2 = new Sale(null, Instant.parse("2024-07-13T03:42:10Z"), SaleStatus.CONFIRMED, u2);
        Sale s3 = new Sale(null, Instant.parse("2024-07-14T14:21:22Z"), SaleStatus.WAITING_PAYMENT, u1);
        Sale s4 = new Sale(null, Instant.parse("2024-07-15T09:15:08Z"), SaleStatus.CONFIRMED, u2);
        Sale s5 = new Sale(null, Instant.parse("2024-07-16T17:30:47Z"), SaleStatus.CANCELED, u3);
        Sale s6 = new Sale(null, Instant.parse("2024-07-17T11:10:54Z"), SaleStatus.CONFIRMED, u4);
        Sale s7 = new Sale(null, Instant.parse("2024-07-17T14:44:19Z"), SaleStatus.CONFIRMED, u5);

        saleRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7));

        // Criando e salvando produtos
        Product p1 = new Product(null, "Notebook", "Dell, Intel Core i7", 4000.00, 8, true);
        Product p2 = new Product(null, "Calular", "Samsung, Galaxy S22", 2980.99, 15, true);
        Product p3 = new Product(null, "TV", "LG 55 polegadas, 4K UHD", 2799.90, 10, true);
        Product p4 = new Product(null, "Fone de Ouvido", "Bluetooth JBL", 598.99, 20, true);
        Product p5 = new Product(null, "Playstation 5", "Console com 1TB", 4699.99, 4, false);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
   System.out.printf("Teste 3");


        // Criando e salvando itens de venda
        SaleItem si1 = new SaleItem(s1, p1, 1, p1.getPrice());
        SaleItem si2 = new SaleItem(s2, p3, 3, p3.getPrice());
        SaleItem si3 = new SaleItem(s3, p2, 2, p2.getPrice());
        SaleItem si4 = new SaleItem(s4, p5, 1, p5.getPrice());
        SaleItem si5 = new SaleItem(s5, p4, 5, p4.getPrice());
        SaleItem si6 = new SaleItem(s6, p2, 1, p2.getPrice());
        SaleItem si7 = new SaleItem(s6, p1, 2, p1.getPrice());

        saleItemRepository.saveAll(Arrays.asList(si1, si2, si3, si4, si5, si6, si7));

        // Criando e salvando pagamento para a venda s1
        Payment pay1 = new Payment(null, Instant.parse("2024-07-13T12:50:07Z"), s2);
        Payment pay2 = new Payment(null, Instant.parse("2024-07-15T14:30:22Z"), s4);
        Payment pay3 = new Payment(null, Instant.parse("2024-07-17T12:00:54Z"), s6);
        Payment pay4 = new Payment(null, Instant.parse("2024-07-17T16:44:19Z"), s7);
        s2.setPayment(pay1);
        s4.setPayment(pay2);
        s6.setPayment(pay3);
        s7.setPayment(pay4);

        saleRepository.saveAll(Arrays.asList(s1, s2, s3, s4));
    }
}
