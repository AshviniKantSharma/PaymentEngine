package com.paymentengine.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Order {
	
	private String id;
	
	private String acknowledgementMode;
	
	private String promotion;
	
	private boolean agentToComission;
	
	@Autowired
	Product prodObj;

}
