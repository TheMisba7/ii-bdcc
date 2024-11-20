package org.example.billingservice.dao;

import org.example.billingservice.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemDao extends JpaRepository<ProductItem, Long> {
}
