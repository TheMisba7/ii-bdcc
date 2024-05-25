package org.mansar.digitalbanking.service;

import org.mansar.digitalbanking.dao.CustomerDao;
import org.mansar.digitalbanking.dto.CustomerDTO;
import org.mansar.digitalbanking.dto.PageContainer;
import org.mansar.digitalbanking.dto.mapper.CustomerMapper;
import org.mansar.digitalbanking.exception.CustomerNotFoundException;
import org.mansar.digitalbanking.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractService<CustomerDTO, Customer, CustomerDao> {

    public CustomerService(CustomerDao customerDao, CustomerMapper customerMapper) {
        super(customerDao, customerMapper);
    }

    public CustomerDTO newCustomer(CustomerDTO customerDTO) {
        Customer customer = dao.save(mapper.fromDTO(customerDTO));
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
}
