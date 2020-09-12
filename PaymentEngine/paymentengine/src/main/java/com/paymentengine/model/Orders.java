package com.paymentengine.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class Orders {
	
	public Orders() {
		// TODO Auto-generated constructor stub
	}

	List<Order> orderList;

}
