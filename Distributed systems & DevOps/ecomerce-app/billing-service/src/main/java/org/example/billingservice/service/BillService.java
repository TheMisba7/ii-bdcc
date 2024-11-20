package org.example.billingservice.service;

import lombok.RequiredArgsConstructor;
import org.example.billingservice.dao.BillDao;
import org.example.billingservice.feign.CustomerRestClient;
import org.example.billingservice.feign.ProductRestClient;
import org.example.billingservice.model.Bill;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillDao billDao;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;


    public Bill getBillById(Long id) {
        Bill bill = billDao.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });
        return bill;
    }
}
