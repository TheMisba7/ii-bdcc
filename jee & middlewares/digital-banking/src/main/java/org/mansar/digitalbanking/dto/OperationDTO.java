package org.mansar.digitalbanking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.mansar.digitalbanking.model.OperationType;

import java.time.LocalDateTime;

@Getter
@Setter
public class OperationDTO {
    private Long id;
    private LocalDateTime createdAt;
    @Min(value = 1)
    private double amount;
    @NotNull
    private OperationType type;
    @NotBlank
    private String accountId;
    private String description;
    private String desAccount; // destination account for transfer operation
}
