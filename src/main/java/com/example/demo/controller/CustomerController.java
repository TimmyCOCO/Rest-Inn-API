package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// get all customer info
	@GetMapping("/Customers")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	// get specific customer info
	@GetMapping("/Customers/{customerId}")
	public Customer getCustomer(@PathVariable String customerId) {
		if(customerService.getCustomer(customerId) !=  null) {
			return customerService.getCustomer(customerId);
		}else {
			return new Customer();
		}

	}

	// add new customers
	@PostMapping("/Customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

}
