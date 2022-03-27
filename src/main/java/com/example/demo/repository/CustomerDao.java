package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Customer;

public interface CustomerDao extends MongoRepository<Customer, String> {

}
