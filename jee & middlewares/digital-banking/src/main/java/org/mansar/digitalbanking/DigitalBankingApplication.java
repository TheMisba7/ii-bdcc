package org.mansar.digitalbanking;

import org.mansar.digitalbanking.dao.BankAccountDao;
import org.mansar.digitalbanking.dao.CustomerDao;
import org.mansar.digitalbanking.dao.OperationDao;
import org.mansar.digitalbanking.model.AccountStatus;
import org.mansar.digitalbanking.model.BankAccount;
import org.mansar.digitalbanking.model.CurrentAccount;
import org.mansar.digitalbanking.model.Customer;
import org.mansar.digitalbanking.service.IBankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootApplication
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerDao customerDao, BankAccountDao bankAccountDao, IBankService bankService, OperationDao operationDao) {
       return args -> {
           operationDao.deleteAll();
           bankAccountDao.deleteAll();
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
           List<CurrentAccount> collect = customerDao.saveAll(customers).stream().map(customer -> {
               CurrentAccount bankAccount = new CurrentAccount();
               bankAccount.setId(UUID.randomUUID().toString());
               bankAccount.setBalance(18728);
               bankAccount.setStatus(AccountStatus.CREATED);
               bankAccount.setOverDraft(100000000);
               bankAccount.setCustomer(customer);
               return bankAccount;
           }).collect(Collectors.toList());
           Random random = new Random();
           bankAccountDao.saveAll(collect)
                   .forEach(bank -> {
                       for (int i = 1; i <= 10; i++) {
                           bankService.credit(
                                   bank.getId(),
                                    random.nextInt(1, 100) * i,
                                   "Test credit" + i
                           );
                       }
                   });
       };
    }

}
