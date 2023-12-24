package org.ws;



import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;
@WebService
public interface BankService {

    /**
     * Convert EURO to DH.
     * @param amount
     * @return result
     */
    @WebMethod
    double convert(double amount);

    /**
     * Find an account by id.
     * @param accountId
     * @return an account
     */
    @WebMethod
    Account getAccount(int accountId);

    /**
     * Get all accounts.
     * @return list of accounts
     */
    @WebMethod
    List<Account> getAccounts();
}
