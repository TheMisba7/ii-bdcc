package org.mansar.digitalbanking.dao;

import org.mansar.digitalbanking.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountDao extends JpaRepository<BankAccount, String> {
}
