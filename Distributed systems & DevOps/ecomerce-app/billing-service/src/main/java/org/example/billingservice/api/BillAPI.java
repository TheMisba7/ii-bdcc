package org.example.billingservice.api;

import lombok.RequiredArgsConstructor;
import org.example.billingservice.model.Bill;
import org.example.billingservice.service.BillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bills")
public class BillAPI {
    private final BillService billService;

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }
}
