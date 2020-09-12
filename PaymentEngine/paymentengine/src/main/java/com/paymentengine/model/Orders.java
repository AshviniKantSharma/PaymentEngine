package com.paymentengine.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class Orders {
	
	List<Order> orderList;
	
	public Orders() {
		// TODO Auto-generated constructor stub
		this.orderList = new ArrayList<Order>();
	}

	

}
