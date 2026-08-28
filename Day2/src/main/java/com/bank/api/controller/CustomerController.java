package com.bank.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.api.model.Customer;
import com.bank.api.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/customers") // This sets the base URL for these endpoints
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ADD
    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    // GET
    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // UPDATE
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody Customer updatedData) {
        return customerService.updateCustomer(id, updatedData);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable String id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return "Customer with ID " + id + " deleted successfully.";
        }
        return "Customer not found.";
    }
}