package com.mavericsystems.demo.repository;

import com.mavericsystems.demo.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer, String> {
}
