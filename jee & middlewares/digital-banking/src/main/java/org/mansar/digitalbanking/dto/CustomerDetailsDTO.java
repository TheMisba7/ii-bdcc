package org.mansar.digitalbanking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDetailsDTO {
    private CustomerDTO customer;
    private List<BankAccountDTO> accounts;
}
