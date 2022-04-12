package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerDao;

@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	CustomerDao customerDao;

	// use bcrypt to encode the password
	@Autowired
	@Lazy // this one is used to solve the cycle problem
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// get all users
	public List<Customer> getAllCustomers() {

		return customerDao.findAll();
	}

	// get a specific user
	public Customer getCustomer(String customerId) {
		Optional<Customer> customer = customerDao.findById(customerId);

		if (customer.isPresent()) {
			return customer.get();
		} else {
			return null;
		}
	}

	// add a user
	public Customer addCustomer(Customer customer) {
		// validation logic: require firstname, lastname, email, password
		if(customer.getEmail() == "" || customer.getFirstName() == ""
				|| customer.getLastName() == "" || customer.getPassword() == "") {
			// do not add into database
			return null;
		}
		
		// use bcrypt to encode the password
		String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
		
		// modify the original password to encrypted password
		customer.setPassword(encodedPassword);
		
		return customerDao.save(customer);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Customer foundUser = customerDao.findByEmail(email);

		String userN = foundUser.getEmail();
		String password = foundUser.getPassword();

		return new User(userN, password, new ArrayList<>());

	}

}
