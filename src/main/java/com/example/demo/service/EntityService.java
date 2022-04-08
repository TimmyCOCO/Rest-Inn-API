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

	// get a specific entity by Id
	public RestInnEntity getEntity(String entityId) {
		Optional<RestInnEntity> entity = entityDao.findById(entityId);

		if (entity.isPresent()) {
			return entity.get();
		} else {
			return null;
		}
	}

	// get entities by title or title
	public List<RestInnEntity> getEntitiesByTitleOrType(String title, String type) {
		return entityDao.findByTitleOrType(title, type);
	}

	// get entities that is best seller
	public List<RestInnEntity> getEntitiesByisBestSeller(Boolean isBestSeller) {
		return entityDao.findByisBestSeller(isBestSeller);
	}

	// add an entity
	public RestInnEntity addEntity(RestInnEntity entity) {
		// validation logic: require title, price, type, location

		if (entity.getTitle() == null || entity.getPrice() == null || entity.getType() == null
				||  entity.getLocation() == null) {
			// do not add into database
			return null;
		}

		return entityDao.save(entity);
	}

	// update an entity
	public RestInnEntity updateEntity(RestInnEntity entity) {
		return entityDao.save(entity);
	}

	// delete an entity
	public void deleteEntity(String entityId) {
		entityDao.deleteById(entityId);
	}

}
