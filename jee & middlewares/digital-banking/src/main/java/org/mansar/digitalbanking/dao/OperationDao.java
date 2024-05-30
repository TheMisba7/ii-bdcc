package org.mansar.digitalbanking.dao;

import org.mansar.digitalbanking.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationDao extends JpaRepository<Operation, Long> {
    List<Operation> findByAccountId(String accountId);
    List<Operation> findAllByAccountIdIn(List<String> ids);
}
