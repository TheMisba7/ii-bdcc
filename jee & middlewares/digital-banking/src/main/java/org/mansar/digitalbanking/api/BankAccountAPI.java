package org.mansar.digitalbanking.api;

import jakarta.validation.Valid;
import org.mansar.digitalbanking.dto.BankAccountDTO;
import org.mansar.digitalbanking.dto.PageContainer;
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
@CrossOrigin("*")
public class BankAccountAPI {
    private final IBankService bankService;

    public BankAccountAPI(IBankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("")
    public BankAccountDTO postAccount(@Valid @RequestBody BankAccountDTO bankAccountDTO) {
        return bankService.create(bankAccountDTO);
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

}
