package org.mansar.digitalbanking.dao;

import org.mansar.digitalbanking.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
    Page<Customer> findByFirstnameContainsOrLastnameContains(String firstname, String lastname, PageRequest pageRequest);
    Customer findByEmail(String email);
}
