package org.example.springmvcthymeleaf.dao;

import org.example.springmvcthymeleaf.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<UserRole, Long> {
    UserRole findByName(String name);
    List<UserRole> findAllByNameIn(List<String> names);
}
