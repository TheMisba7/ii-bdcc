package org.mansar.digitalbanking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mansar.digitalbanking.model.AccountStatus;
import org.mansar.digitalbanking.model.Operation;
import org.mansar.digitalbanking.model.enums.AccountType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {
    private String id;
    @Min(value = 100, message = "Initial balance should be greater or equal 100")
    private double balance;
    private LocalDateTime createdAt;
    private AccountStatus status;
    @NotNull(message = "customer id is required")
    private Long customerId;
    private List<Operation> operations;
    @NotNull(message = "Account types is required")
    private AccountType type;
    private double interestRate;
    private double overDraft;
}
