package org.mansar.digitalbanking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DashboardDTO {
    //for admin dashboard
    private List<CustomerDTO> customers;
    private List<BankAccountDTO> bankAccounts;
    private long totalCustomers;
    private long totalAccounts;

    //common
    private long totalOperations;
    private List<OperationDTO> operations;

    //for customer dashboard
    private BankAccountDTO account;
}
