package org.ws.impl;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.ws.Account;
import org.ws.BankService;

import java.util.Date;
import java.util.List;

@WebService(name = "bankServiceWS")
public class BankServiceImpl implements BankService {
    @Override
    @WebMethod
    public double convert(double amount) {
        return amount * 11;
    }

    @Override
    @WebMethod
    public Account getAccount(int accountId) {
        return new Account(accountId, (int) (Math.random() * accountId),
                Math.random() * 60, new Date());
    }

    @Override
    @WebMethod
    public List<Account> getAccounts() {
        return  List.of(
                new Account(1, (int) (Math.random() * 1),
                        Math.random() * 60, new Date()),
                new Account(2, (int) (Math.random() * 2),
                        Math.random() * 60, new Date()),
                new Account(3, (int) (Math.random() * 3),
                        Math.random() * 60, new Date()),
                new Account(4, (int) (Math.random() * 4),
                        Math.random() * 60, new Date())
        );
    }
}
