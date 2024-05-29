package org.mansar.digitalbanking.service;

import org.mansar.digitalbanking.dao.AgentRoleDao;
import org.mansar.digitalbanking.dao.BankAccountDao;
import org.mansar.digitalbanking.dao.CustomerDao;
import org.mansar.digitalbanking.dto.BankAccountDTO;
import org.mansar.digitalbanking.dto.CustomerDTO;
import org.mansar.digitalbanking.dto.CustomerDetailsDTO;
import org.mansar.digitalbanking.dto.NewCustomerDTO;
import org.mansar.digitalbanking.dto.PageContainer;
import org.mansar.digitalbanking.dto.mapper.BankAccountMapper;
import org.mansar.digitalbanking.dto.mapper.CustomerMapper;
import org.mansar.digitalbanking.exception.CustomerNotFoundException;
import org.mansar.digitalbanking.model.BankAccount;
import org.mansar.digitalbanking.model.Customer;
import org.mansar.digitalbanking.model.Email;
import org.mansar.digitalbanking.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService extends AbstractService<CustomerDTO, Customer, CustomerDao> {
    private final BankAccountDao bankAccountDao;
    private final BankAccountMapper bankAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final INotification notification;
    private final CustomerMapper customerMapper;
    private final AgentRoleDao agentRoleDao;
    public CustomerService(CustomerDao customerDao, CustomerMapper customerMapper,
                           BankAccountDao bankAccountDao, BankAccountMapper bankAccountMapper,
                           PasswordEncoder passwordEncoder, INotification notification,
                           AgentRoleDao agentRoleDao) {
        super(customerDao, customerMapper);
        this.bankAccountDao = bankAccountDao;
        this.bankAccountMapper = bankAccountMapper;
        this.passwordEncoder = passwordEncoder;
        this.notification = notification;
        this.customerMapper = customerMapper;
        this.agentRoleDao = agentRoleDao;
    }


    @Secured("ROLE_ADMIN")
    public CustomerDTO newCustomer(NewCustomerDTO customerDTO) {
        Customer customer = customerMapper.fromDTO(customerDTO);
        String generatedPassword = Utils.generateRandomPassword(10);
        customer.setPassword(passwordEncoder.encode(generatedPassword));
        customer.setRoles(agentRoleDao.findAllById(customerDTO.getRoleIds()));
        customer = dao.save(customer);
        Email email = Utils.welcomeEmail(customerDTO, generatedPassword);
        notification.send(email);
        return mapper.toDTO(customer);
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = getById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        return mapper.toDTO(customer);
    }

    public PageContainer<CustomerDTO> getCustomers(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Customer> customers = dao.findByFirstnameContainsOrLastnameContains(keyword, keyword, pageRequest);
        return super.map(customers);
    }

    public CustomerDetailsDTO getCustomerDetails(long customerId) {
        return new CustomerDetailsDTO(
                getCustomerById(customerId),
                getCustomerAccounts(customerId)
        );
    }

    private List<BankAccountDTO> getCustomerAccounts(long customerId) {
        //todo verify that the connected customer id is the same as this id or the connected user id is admin
        List<BankAccount> bankAccounts = bankAccountDao.findByCustomerId(customerId);
        return bankAccountMapper.toDTO(bankAccounts);
    }

    public void deleteCustomer(long customerId) {

    }
}
