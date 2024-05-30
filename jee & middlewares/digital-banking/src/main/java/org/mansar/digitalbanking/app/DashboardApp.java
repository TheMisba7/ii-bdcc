package org.mansar.digitalbanking.app;

import lombok.RequiredArgsConstructor;
import org.mansar.digitalbanking.dao.BankAccountDao;
import org.mansar.digitalbanking.dao.CustomerDao;
import org.mansar.digitalbanking.dao.OperationDao;
import org.mansar.digitalbanking.dto.DashboardDTO;
import org.mansar.digitalbanking.dto.mapper.BankAccountMapper;
import org.mansar.digitalbanking.dto.mapper.CustomerMapper;
import org.mansar.digitalbanking.dto.mapper.OperationMapper;
import org.mansar.digitalbanking.model.BankAccount;
import org.mansar.digitalbanking.model.Customer;
import org.mansar.digitalbanking.model.Operation;
import org.mansar.digitalbanking.service.CustomerService;
import org.mansar.digitalbanking.service.IBankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardApp {
    private final CustomerDao customerDao;
    private final BankAccountDao bankAccountDao;
    private final OperationDao operationDao;
    private final CustomerMapper customerMapper;
    private final BankAccountMapper bankAccountMapper;
    private final OperationMapper operationMapper;
    private final IBankService iBankService;

    public DashboardDTO getDashboard() {
        Customer currentCustomer = CustomerService.getCurrentCustomer();
        if (currentCustomer.isAdmin())
            return getAdminDashboard();

        //customer dashboard
        DashboardDTO dashboardDTO = new DashboardDTO();
        List<BankAccount> bankAccounts = bankAccountDao.findByCustomerId(currentCustomer.getId());
        dashboardDTO.setBankAccounts(bankAccountMapper.toDTO(bankAccounts));
        dashboardDTO.setTotalAccounts(bankAccounts.size());
        List<Operation> operations = operationDao.findAllByAccountIdIn(bankAccounts.stream().map(BankAccount::getId).toList());
        dashboardDTO.setOperations(operationMapper.toDTO(operations));
        dashboardDTO.setTotalOperations(operations.size());


        return dashboardDTO;
    }

    private DashboardDTO getAdminDashboard() {
        DashboardDTO dashboard = new DashboardDTO();
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        //fetch latest 10 customers
        Page<Customer> latestCustomer = customerDao.findAll(pageable);
        dashboard.setCustomers(customerMapper.toDTO(latestCustomer.getContent()));
        dashboard.setTotalCustomers(latestCustomer.getTotalElements());
        //fetch latest 10 bank accounts
        Page<BankAccount> latestBankAccounts = bankAccountDao.findAll(pageable);
        dashboard.setBankAccounts(bankAccountMapper.toDTO(latestBankAccounts.getContent()));
        dashboard.setTotalAccounts(latestBankAccounts.getTotalElements());
        //fetch latest 10 operations
        Page<Operation> latestOperations = operationDao.findAll(pageable);
        dashboard.setOperations(operationMapper.toDTO(latestOperations.getContent()));
        dashboard.setTotalOperations(latestOperations.getTotalElements());
        return dashboard;
    }
}
