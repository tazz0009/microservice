package com.tazz009.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.email(request.getEmail()).build();
		// todo: check if email valid
		// todo: check if email not taken
		customerRepository.save(customer);
	}
	
}
