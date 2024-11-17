package org.example.inventoryservice;

import org.example.inventoryservice.dao.ProductDao;
import org.example.inventoryservice.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(ProductDao productDao) {
        return args -> {
            productDao.save(Product.builder()
                            .name("Computer")
                            .price(1092D)
                            .quantity(19)
                    .build());
            productDao.save(Product.builder()
                    .name("Phone")
                    .price(182D)
                    .quantity(10)
                    .build());
            productDao.save(Product.builder()
                    .name("Tablet")
                    .price(9782D)
                    .quantity(30)
                    .build());
            productDao.save(Product.builder()
                    .name("Printer")
                    .price(12D)
                    .quantity(100)
                    .build());

            productDao.findAll().forEach(p -> {
                System.out.println("===============");
                System.out.println(p.toString());
            });
        };
    }
}
