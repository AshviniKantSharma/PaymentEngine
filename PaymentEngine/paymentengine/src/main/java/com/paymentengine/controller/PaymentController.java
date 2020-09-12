package com.paymentengine.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paymentengine.model.PaymentInitiationRequest;
import com.paymentengine.model.PaymentResponse;
import com.paymentengine.service.PaymentService;



/**
 * PaymentController : Controller Class to handle incoming requests for initiating payments
 */
@Slf4j
@Controller
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService payService;

	/** initiatePayment : method to validate incoming request
	 * @param paymentInitiationReq
	 * @param request
	 * @return PaymentResponse
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@PostMapping(path = "/v1.0.0/initiate-order",consumes = "application/json", produces = "application/json")
	public PaymentResponse initiatePayment(
			@RequestBody PaymentInitiationRequest paymentInitiationReq
			) throws Exception {
		log.info("##########PaymentController: Reached initiatePayment##########");
		
		log.info("##########PaymentController: paymentInitiationReq Object"+paymentInitiationReq);
		PaymentResponse paymentRespObj = payService.facilitatePaymentAndOrder(paymentInitiationReq);
		
		
		return new PaymentResponse();
	}

}
