package com.rabopay.service;

import java.util.HashMap;
import java.util.UUID;

import com.rabopay.model.PaymentInitiationRequest;


/**
 * RaboPayService : Interface to implement service level handling of TppValidator
 * @author Ashvini
 *
 */
public interface RaboPayService {
	
	public HashMap<String, Object> tppValidator(PaymentInitiationRequest paymentInitiationReq) throws Exception;
	
	public int calculateIbanSumOfDebtor(PaymentInitiationRequest paymentInitiationReq) throws Exception;
	
	default String uuidGenerator () {
		return UUID.randomUUID().toString();
	}

}
