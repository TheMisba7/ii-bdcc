package org.mansar.digitalbanking;

import org.mansar.digitalbanking.dao.CustomerDao;
import org.mansar.digitalbanking.model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerDao customerDao) {
       return args -> {
           customerDao.deleteAll();
           String firstname = "Abdeddaim";
           String lastname = "Mansar";
           String email = "@gmail.com";
           List<Customer> customers = new ArrayList<>(500);
           for (int i = 1; i <= 500; i++) {
               Customer customer = new Customer();
               customer.setFirstname(firstname + i);
               customer.setLastname(lastname + i);
               customer.setEmail(customer.getLastname() + email);
               customers.add(customer);
           }
           customerDao.saveAll(customers);
       };
    }

}
