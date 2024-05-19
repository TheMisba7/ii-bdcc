package org.mansar.digitalbanking.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mansar.digitalbanking.model.enums.AccountType;

@Entity
@Getter @Setter
@DiscriminatorValue("SA")
@SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public class SavingAccount extends BankAccount{
    private double interestRate;
    @Transient
    private final AccountType type = AccountType.SAVING;
}
