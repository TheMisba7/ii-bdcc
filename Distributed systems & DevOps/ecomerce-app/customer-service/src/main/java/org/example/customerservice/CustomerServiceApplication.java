package org.example.customerservice;

import org.example.customerservice.dao.CustomerDao;
import org.example.customerservice.model.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(CustomerDao customerDao) {
		return args -> {
			customerDao.save(new Customer(null, "Abdeddaim", "abdeddaim@gmail.com"));
			customerDao.save(new Customer(null, "Youssef", "youssef@gmail.com"));
			customerDao.save(new Customer(null, "Mohammed", "mohammed@gmail.com"));
			customerDao.save(new Customer(null, "Omar", "omar@gmail.com"));
			customerDao.save(new Customer(null, "Moad", "moad@gmail.com"));

			customerDao.findAll()
					.forEach(cu -> {
						System.out.println("===============");
						System.out.println(cu);
					});
		};
	}
}
