package org.ws;

import java.util.Date;

public class Account {
    private int id;
    private int code;
    private double balance;
    private Date createdAt;

    public Account(int id, int code,
                   double balance, Date createdAt) {
        this.id = id;
        this.code = code;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
