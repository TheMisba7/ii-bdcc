package org.example.inventoryservice.dao;

import org.example.inventoryservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductDao extends JpaRepository<Product, Long> {
}
