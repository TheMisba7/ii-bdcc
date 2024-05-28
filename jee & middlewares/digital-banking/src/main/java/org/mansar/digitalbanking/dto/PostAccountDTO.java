package org.mansar.digitalbanking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mansar.digitalbanking.model.enums.AccountType;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostAccountDTO {
    @Min(value = 100, message = "Initial balance should be greater or equal 100")
    private double balance;
    @NotNull(message = "customer id is required")
    private Long customerId;
    @NotNull(message = "Account types is required")
    private AccountType type;
    private double interestRate;
    private double overDraft;
}
