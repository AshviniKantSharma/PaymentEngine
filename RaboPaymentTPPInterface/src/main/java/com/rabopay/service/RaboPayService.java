package com.rabopay.service;

import java.util.HashMap;

import com.rabopay.model.PaymentInitiationRequest;


public interface RaboPayService {
	
	public HashMap<String, Object> tppValidator(PaymentInitiationRequest paymentInitiationReq);

}
