package com.example.demo.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Entity")
public class RestInnEntity {

	@Id
	private String id;
	private String image; // ?
	private String title;
	private String description;
	private BigDecimal price;
	private String type;
	private String rules;
	private String Amenities;
	private String location;

	public RestInnEntity() {
		super();
	}

	public RestInnEntity(String id, String image, String title, String description, BigDecimal price, String type,
			String rules, String amenities, String location) {
		super();
		this.id = id;
		this.image = image;
		this.title = title;
		this.description = description;
		this.price = price;
		this.type = type;
		this.rules = rules;
		Amenities = amenities;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getAmenities() {
		return Amenities;
	}

	public void setAmenities(String amenities) {
		Amenities = amenities;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
