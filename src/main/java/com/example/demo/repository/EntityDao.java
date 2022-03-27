package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.RestInnEntity;

public interface EntityDao extends MongoRepository<RestInnEntity, String> {

}
