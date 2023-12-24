package org.ws;

import org.ws.impl.BankServiceImpl;
import jakarta.xml.ws.Endpoint;

public class WSApplication {

    public static void main(String[] args) {
        String url = "http://0.0.0.0:8080/";
        Endpoint.publish(url, new BankServiceImpl());
        System.out.println("WS deployed at: " + url);
    }
}
