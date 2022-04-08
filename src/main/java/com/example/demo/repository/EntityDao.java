package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.RestInnEntity;

public interface EntityDao extends MongoRepository<RestInnEntity, String> {
	// get entities by type
	List<RestInnEntity> findByTitleOrType(String title, String type);
	
	// get entities that is best seller
	List<RestInnEntity> findByisBestSeller(Boolean isBestSeller);
}
