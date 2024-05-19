package org.mansar.digitalbanking.api;

import jakarta.validation.Valid;
import org.mansar.digitalbanking.dto.BankAccountDTO;
import org.mansar.digitalbanking.service.IBankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BankAccountAPI {
    private final IBankService bankService;

    public BankAccountAPI(IBankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/accounts")
    public BankAccountDTO postAccount(@Valid @RequestBody BankAccountDTO bankAccountDTO) {
        return bankService.create(bankAccountDTO);
    }


    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getAccount(@PathVariable String accountId) {
        return bankService.getAccount(accountId);
    }

}
