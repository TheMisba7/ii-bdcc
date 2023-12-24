package org.example;

import wsProxy.Account;
import wsProxy.BankServiceImplService;
import wsProxy.BankServiceWS;

import java.net.MalformedURLException;
import java.net.URL;

public class WsClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/?wsdl");
        BankServiceImplService ws = new BankServiceImplService(url);
        BankServiceWS bankServiceWSPort = ws.getBankServiceWSPort();

        bankServiceWSPort.getAccounts()
                .forEach(WsClient::printAccount);

        printAccount(bankServiceWSPort.getAccount(12));
    }

    private static void printAccount(final Account account) {
        String builder =
                "Id: " + account.getId() +
                " Code: " + account.getCode() +
                " Balance: " + account.getBalance();
       System.out.println(builder);
    }
}