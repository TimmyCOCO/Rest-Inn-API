package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.RestInnEntity;

public interface EntityDao extends MongoRepository<RestInnEntity, String> {
	// get a specific entity by type
	List<RestInnEntity> findByType(String type);
}
