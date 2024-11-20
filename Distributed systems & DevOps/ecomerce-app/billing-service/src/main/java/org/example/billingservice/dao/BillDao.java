package org.example.billingservice.dao;

import org.example.billingservice.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDao extends JpaRepository<Bill, Long> {
}
