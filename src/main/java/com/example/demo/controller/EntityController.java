package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RestInnEntity;
import com.example.demo.service.EntityService;

@RestController
public class EntityController {

	@Autowired
	private EntityService entityService;

	// get all entities
	@GetMapping("/Entities")
	public List<RestInnEntity> getAllEntities() {
		return entityService.getAllEntities();
	}

	// get specific entity
	@GetMapping("/Entities/{entityId}")
	public RestInnEntity getEntity(@PathVariable String entityId) {
		if (entityService.getEntity(entityId) != null) {
			return entityService.getEntity(entityId);
		} else {
			return new RestInnEntity();
		}
	}

	// add new entities
	@PostMapping("/Entities")
	public RestInnEntity addEntity(@RequestBody RestInnEntity entity) {
		return entityService.addEntity(entity);
	}

	// delete specific entity
	@DeleteMapping("/Entities/{entityId}")
	public String deleteEntity(@PathVariable String entityId) {
		if(entityService.getEntity(entityId) != null) {
			entityService.deleteEntity(entityId);
			return "Entity deleted: " + entityId;
		}else {
			return "Entity not found with " + entityId;
		}
		
	}
}
