package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

@RestController
@CrossOrigin(origins="*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	private Customer customer;
	private List<Customer> customerList;

	// get all customer info
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		customerList = customerService.getAllCustomers();

		if(!customerList.isEmpty()) {
			return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// get specific customer info
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String customerId) {
		customer = customerService.getCustomer(customerId);
		
		if(customer !=  null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}else {
			return new ResponseEntity<Customer>(new Customer(), HttpStatus.NOT_FOUND);
		}

	}

	// add new customers
	@PostMapping("/customers")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		
		customer = customerService.addCustomer(customer);
		
		if(customer != null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.CREATED); 
		}else {
			return new ResponseEntity<String>("Error: missing data ",HttpStatus.BAD_REQUEST);
		}
		
		
	}

}
