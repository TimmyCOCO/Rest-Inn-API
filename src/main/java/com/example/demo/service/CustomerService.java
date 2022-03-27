package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerDao;

@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;

	public List<Customer> getAllCustomers() {

		return customerDao.findAll();
	}

	public Customer getCustomer(String customerId) {
		Optional<Customer> customer = customerDao.findById(customerId);

		if (customer.isPresent()) {
			return customer.get();
		} else {
			return new Customer();
		}
	}

	public Customer addCustomer(Customer customer) {
		return customerDao.save(customer);
	}

}
