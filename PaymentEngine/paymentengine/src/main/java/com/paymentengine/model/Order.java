package com.paymentengine.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
	
	private String id;
	
	private String acknowledgementMode;
	
	private String promotion;
	
	Product prodObj;

}
