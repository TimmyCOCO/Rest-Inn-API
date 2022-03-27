package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RestInnEntity;
import com.example.demo.repository.EntityDao;

@Service
public class EntityService {

	@Autowired
	EntityDao entityDao;

	// get all entities
	public List<RestInnEntity> getAllEntities() {

		return entityDao.findAll();
	}

	// get specific entity
	public RestInnEntity getEntity(String entityId) {
		Optional<RestInnEntity> entity = entityDao.findById(entityId);

		if (entity.isPresent()) {
			return entity.get();
		}else {
			return null;
		}
	}

	// add an entity
	public RestInnEntity addEntity(RestInnEntity entity) {
		return entityDao.save(entity);
	}

	// delete an entity
	public void deleteEntity(String entityId) {
		entityDao.deleteById(entityId);
	}
	
	

}
