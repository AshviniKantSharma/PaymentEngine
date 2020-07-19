package com.rabopay.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRejectedResponse {
	
	private String status;
	
	private String reason;
	
	private String reasonCode;

}
