package com.tazz009.microservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckResponse {

	private boolean isFraudulentCustomer;
	
}
