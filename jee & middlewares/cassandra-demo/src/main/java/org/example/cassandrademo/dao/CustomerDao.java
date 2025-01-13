package org.example.cassandrademo.dao;

import org.example.cassandrademo.model.Customer;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerDao extends CassandraRepository<Customer, String> {
}
