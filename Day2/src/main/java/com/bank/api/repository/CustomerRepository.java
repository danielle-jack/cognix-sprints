package com.bank.api.repository;

import com.bank.api.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// @Repository tells Spring this file is responsible for database communication
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    // By simply extending MongoRepository, Spring automatically gives you methods like:
    // save(), findById(), findAll(), and deleteById() for free!
}