package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;

// This is for authentication
@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth")
	public ResponseEntity<String> login(@RequestBody Customer customer) {

		try {

			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword()));

			return new ResponseEntity<String>("Login successfully", HttpStatus.OK);

		} catch (BadCredentialsException ex) {

			return new ResponseEntity<String>("Your email/password entered incorrectly", HttpStatus.UNAUTHORIZED);
		}

	}
}
