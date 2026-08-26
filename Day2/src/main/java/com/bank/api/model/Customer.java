package com.bank.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;
import java.util.TreeSet;

// @Document tells Spring to save these objects into a MongoDB collection called "customers"
@Document(collection = "customers")
public class Customer {

    // @Id tells MongoDB to use this as the unique document identifier (the primary key)
    @Id
    private String id;
    
    private String username;
    private String password;
    private Set<String> accountIds;

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountIds = new TreeSet<>();
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Set<String> getAccountIds() { return accountIds; }
    public void addAccountId(String accountId) { this.accountIds.add(accountId); }
}