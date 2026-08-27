package com.bank.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.api.model.Customer;
import com.bank.api.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    // ADD/POST
    public Customer createCustomer(Customer customer) {
        // The repository's built-in save() method automatically inserts it into MongoDB
        return customerRepo.save(customer);
    }

    // GET
    public java.util.List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    // UPDATE
    public Customer updateCustomer(String id, Customer updatedData) {
        Customer existing = customerRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setUsername(updatedData.getUsername());
            existing.setPassword(updatedData.getPassword());
            return customerRepo.save(existing);
        }
        return null;
    }

    // DELETE
    public boolean deleteCustomer(String id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return true;
        }
        return false;
    }
}