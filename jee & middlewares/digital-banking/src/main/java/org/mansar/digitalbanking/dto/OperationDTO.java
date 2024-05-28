package org.mansar.digitalbanking.dto;

import lombok.Getter;
import lombok.Setter;
import org.mansar.digitalbanking.model.OperationType;

import java.time.LocalDateTime;

@Getter
@Setter
public class OperationDTO {
    private Long id;
    private LocalDateTime createdAt;
    private double amount;
    private OperationType type;
    private String accountId;
    private String description;
}
