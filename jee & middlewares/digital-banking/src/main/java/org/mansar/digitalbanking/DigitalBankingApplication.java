package org.mansar.digitalbanking;

import org.mansar.digitalbanking.dao.AgentRoleDao;
import org.mansar.digitalbanking.dao.BankAccountDao;
import org.mansar.digitalbanking.dao.CustomerDao;
import org.mansar.digitalbanking.dao.OperationDao;
import org.mansar.digitalbanking.model.AccountStatus;
import org.mansar.digitalbanking.model.AgentRole;
import org.mansar.digitalbanking.model.BankAccount;
import org.mansar.digitalbanking.model.CurrentAccount;
import org.mansar.digitalbanking.model.Customer;
import org.mansar.digitalbanking.model.Email;
import org.mansar.digitalbanking.service.IBankService;
import org.mansar.digitalbanking.service.INotification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    CommandLineRunner commandLineRunner(CustomerDao customerDao,
                                        PasswordEncoder passwordEncoder,
                                        AgentRoleDao agentRoleDao) {
       return args -> {
           agentRoleDao.deleteAll();
           AgentRole admin = new AgentRole();
           admin.setName("ADMIN");
           AgentRole customerRole = new AgentRole();
           customerRole.setName("CUSTOMER");

           agentRoleDao.saveAll(List.of(admin, customerRole));
           Customer customer = new Customer();
           customer.setLastname("Mansar");
           customer.setFirstname("Abdeddaim");
           customer.setEmail("a.mansar@nuitee.com");
           customer.setRoles(List.of(agentRoleDao.findByName("ADMIN")));
           customer.setPassword(passwordEncoder.encode("whynot"));
           customerDao.save(customer);
       };
    }
}
