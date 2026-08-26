package com.bank.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.model.Customer;
import com.bank.api.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    // This fulfills the "createCustomer" requirement from your teacher
    public Customer createCustomer(Customer customer) {
        // The repository's built-in save() method automatically inserts it into MongoDB
        return customerRepo.save(customer);
    }
}