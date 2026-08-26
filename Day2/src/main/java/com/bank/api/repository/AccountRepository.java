package com.bank.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bank.api.model.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    
    // writes the MongoDB query to fetch accounts matching that ID
    List<Account> findByCustomerId(String customerId);

    //find accounts with a balance higher than the threshold parameter
    List<Account> findByBalanceGreaterThan(double threshold);
}