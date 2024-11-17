package org.example.customerservice.dao;

import org.example.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface CustomerDao extends JpaRepository<Customer, Long> {
}
