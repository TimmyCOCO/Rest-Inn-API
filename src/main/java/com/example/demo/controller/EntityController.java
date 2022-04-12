package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.RestInnEntity;
import com.example.demo.service.EntityService;

@RestController
@CrossOrigin(origins="*")
public class EntityController {

	@Autowired
	private EntityService entityService;

	private RestInnEntity restInnEntityId; // for entity id
	private RestInnEntity restInnEntityContent;
	private List<RestInnEntity> restInnEntityListByTitleOrType; // for store list of entities
	private List<RestInnEntity> restInnEntityList;

//	// get all entities
//	@GetMapping(value = "/entities", params = { "!*" })
//	public ResponseEntity<List<RestInnEntity>> getAllEntities() {
//		restInnEntityList = entityService.getAllEntities();
//
//		if (!restInnEntityList.isEmpty()) {
//			return new ResponseEntity<List<RestInnEntity>>(restInnEntityList, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}

	// get a specific entity by Id
	@GetMapping("/entities/{entityId}")
	public ResponseEntity<RestInnEntity> getEntity(@PathVariable String entityId) {

		restInnEntityId = entityService.getEntity(entityId);

		if (restInnEntityId != null) {
			return new ResponseEntity<RestInnEntity>(restInnEntityId, HttpStatus.OK);

		} else {
			return new ResponseEntity<RestInnEntity>(HttpStatus.NOT_FOUND);
		}
	}

	// get all entities or get specific entities by title or type
	@GetMapping("/entities")
	public ResponseEntity<List<RestInnEntity>> getEntitiesByTitleOrType(@RequestParam String title,
			@RequestParam String type) {
		
		restInnEntityListByTitleOrType = entityService.getEntitiesByTitleOrType(title, type);
		
		// ?title=&type= -> will retrieve all entities
		if (title == "" && type == "") {
			
			restInnEntityList = entityService.getAllEntities();
			return new ResponseEntity<List<RestInnEntity>>(restInnEntityList, HttpStatus.OK);

		} else if (!restInnEntityListByTitleOrType.isEmpty()) {
			// get specific entities
			return new ResponseEntity<List<RestInnEntity>>(restInnEntityListByTitleOrType, HttpStatus.OK);
			
		} else {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// get entities that is best seller
	@GetMapping(value = "/entities/bestSeller")
	public ResponseEntity<List<RestInnEntity>> getEntitiesByisBestSeller(@RequestParam Boolean isBestSeller) {
		restInnEntityList = entityService.getEntitiesByisBestSeller(isBestSeller);

		if (!restInnEntityList.isEmpty()) {
			return new ResponseEntity<List<RestInnEntity>>(restInnEntityList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// add new entities
	@PostMapping("/entities")
	public ResponseEntity<?> addEntity(@RequestBody RestInnEntity entity) {
		restInnEntityContent = entityService.addEntity(entity);

		if (restInnEntityContent != null) {
			return new ResponseEntity<RestInnEntity>(restInnEntityContent, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Error:missing data", HttpStatus.BAD_REQUEST) ;
		}

	}

	// update an entity
	@PutMapping("/entities/{entityId}")
	public ResponseEntity<?> updateEntity(@RequestBody RestInnEntity entity,
			@PathVariable String entityId) {

		entity.setId(entityId);

		restInnEntityId = entityService.getEntity(entityId);
		restInnEntityContent = entityService.addEntity(entity);
		
		// if have specific id, then update the entity
		if (restInnEntityId != null) {
			
			if(restInnEntityContent == null) {
				return new ResponseEntity<>("Error:missing data", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<RestInnEntity>(restInnEntityContent, HttpStatus.OK);
		} else {
			// if don't have specific id
			return new ResponseEntity<>("Cannot find the id with " + entityId, HttpStatus.BAD_REQUEST);
		}
	}

	// delete specific entity
	@DeleteMapping("/entities/{entityId}")
	public ResponseEntity<String> deleteEntity(@PathVariable String entityId) {
		restInnEntityId = entityService.getEntity(entityId);

		if (restInnEntityId != null) {
			entityService.deleteEntity(entityId);
			return new ResponseEntity<String>("Entity deleted: " + entityId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Entity not found with " + entityId, HttpStatus.NOT_FOUND);
		}
	}
}
