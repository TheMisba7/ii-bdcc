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
@SuperBuilder
@Setter @Getter
@DiscriminatorValue("CA")
@AllArgsConstructor @NoArgsConstructor
public class CurrentAccount extends BankAccount {
    private double overDraft;
    @Transient
    private final AccountType type = AccountType.CURRENT;
}
