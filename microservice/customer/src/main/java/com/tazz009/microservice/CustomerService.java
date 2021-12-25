package com.tazz009.microservice;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final RestTemplate restTemplate;

	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.email(request.getEmail()).build();
		// todo: check if email valid
		// todo: check if email not taken
		// todo: check if fraudster
		log.info("{}", customer);
		customerRepository.saveAndFlush(customer);
		FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerId}",
				FraudCheckResponse.class, customer.getId());
		if (fraudCheckResponse.isFraudulentCustomer()) {
			throw new IllegalStateException("fraudster");
		}
		customerRepository.save(customer);
		// todo: send notification

	}

}
