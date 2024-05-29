package org.mansar.digitalbanking.api;

import jakarta.validation.Valid;
import org.mansar.digitalbanking.app.AccountDetailsApp;
import org.mansar.digitalbanking.dto.BankAccountDTO;
import org.mansar.digitalbanking.dto.OperationDTO;
import org.mansar.digitalbanking.dto.PageContainer;
import org.mansar.digitalbanking.dto.PostAccountDTO;
import org.mansar.digitalbanking.model.AccountStatus;
import org.mansar.digitalbanking.service.IBankService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountAPI {
    private final IBankService bankService;
    private final AccountDetailsApp accountDetailsApp;

    public BankAccountAPI(IBankService bankService, AccountDetailsApp accountDetailsApp) {
        this.bankService = bankService;
        this.accountDetailsApp = accountDetailsApp;
    }

    @PostMapping("")
    public BankAccountDTO postAccount(@Valid @RequestBody PostAccountDTO postAccountDTO) {
        return bankService.create(postAccountDTO);
    }

    @GetMapping
    public PageContainer<BankAccountDTO> getAccounts(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "100") int size) {
        return bankService.getAccounts(page, size);
    }


    @GetMapping("/{accountId}")
    public BankAccountDTO getAccount(@PathVariable String accountId) {
        return bankService.getAccount(accountId);
    }

    @PutMapping("/{accountId}/status")
    public void updateStatus(@PathVariable String accountId,
                             @RequestParam(name = "newStatus") AccountStatus status) {
        bankService.updateStatus(accountId, status);
    }
    @PostMapping("/operations")
    public void newOperation(@RequestBody OperationDTO operationDTO) {
         accountDetailsApp.createOperation(operationDTO);
    }


}
