package com.paymentengine.service;

import java.util.UUID;

import com.paymentengine.model.PaymentInitiationRequest;
import com.paymentengine.model.PaymentResponse;


/**
 * PaymentService : Interface to implement service level handling of PaymentEngine
 * @author Ashvini
 *
 */
public interface PaymentService {
	
	public PaymentResponse facilitatePaymentAndOrder(PaymentInitiationRequest paymentReqObj) throws Exception;
	
	default String uuidGenerator () {
		return UUID.randomUUID().toString();
	}

}
