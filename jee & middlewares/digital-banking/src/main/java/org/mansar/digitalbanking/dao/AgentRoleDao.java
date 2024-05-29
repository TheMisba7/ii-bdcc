package org.mansar.digitalbanking.dao;

import org.mansar.digitalbanking.model.AgentRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRoleDao extends JpaRepository<AgentRole, Long> {
    AgentRole findByName(String name);
}
