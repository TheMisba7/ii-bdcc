package org.example.springmvcthymeleaf.dao;

import org.example.springmvcthymeleaf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
