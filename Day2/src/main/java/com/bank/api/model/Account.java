package com.bank.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
public class Account {

    @Id
    private String id;
    
    private String customerId; // This links the account to a specific customer
    private double balance;
    private String accountType; // Savings or Checking

    public Account(String customerId, double balance, String accountType) {
        this.customerId = customerId;
        this.balance = balance;
        this.accountType = accountType;
    }

    // Getters and Setters
    public String getId() { return id; }
    
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
}