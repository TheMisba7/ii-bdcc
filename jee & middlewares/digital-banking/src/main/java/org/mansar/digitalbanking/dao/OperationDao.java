package org.mansar.digitalbanking.dao;

import org.mansar.digitalbanking.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationDao extends JpaRepository<Operation, Long> {
}
