package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.entity.Customer;


public interface CustomerDao extends MongoRepository<Customer, String> {
	
	// tells mongo that there needs to be an implementation for this
	// get a user by email
	Customer findByEmail(String email);
	
}
