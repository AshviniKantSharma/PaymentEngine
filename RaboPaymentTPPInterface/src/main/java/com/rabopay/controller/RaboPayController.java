package com.rabopay.controller;

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

import com.rabopay.model.PaymentAcceptedResponse;
import com.rabopay.model.PaymentInitiationRequest;
import com.rabopay.model.PaymentRejectedResponse;
import com.rabopay.service.RaboPayService;
import com.rabopay.util.AuthorizeRequests;
import com.rabopay.util.Constants;

/**
 * RaboPayController : Controller Class to handle incoming requests for initiating payments
 */
@Slf4j
@Controller
@RequestMapping("/rabopay")
public class RaboPayController {
	
	@Autowired
	RaboPayService raboPayService;

	/** initiatePayment : method to validate incoming request
	 * @param paymentInitiationReq
	 * @param request
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@PostMapping(path = "/v1.0.0/initiate-payment",consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> initiatePayment(
			@RequestBody PaymentInitiationRequest paymentInitiationReq,
			HttpServletRequest request
			) throws Exception {
		log.info("##########RaboPayController: Reached initiatePayment##########");
		
		log.info("##########RaboPayController: paymentInitiationReq Object"+paymentInitiationReq);
		HashMap<String,Object> serObj = raboPayService.tppValidator(paymentInitiationReq);
		
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		
		String headerId = request.getHeader("X-Request-Id");
		log.info("Retrieve Auth Object");
		
		AuthorizeRequests authReq = AuthorizeRequests.requestInstance();
		
		String signature = authReq.signVerifyPaymentRequest(paymentInitiationReq, request);
		String signatureCertificate = request.getHeader("Signature-Certificate");
		
		headers.add("X-Request-Id", headerId);
		headers.add("Signature", signature);
		headers.add("Signature-Certificate", signatureCertificate);
		System.out.println("serObj"+serObj);
		if(serObj.entrySet().iterator().hasNext() && serObj.entrySet().iterator().next().getKey().equals(Constants.PaymentRejectedResponse)) {
			log.info("Rejecting Response");
		return new ResponseEntity<Object>((PaymentRejectedResponse)serObj.get(Constants.PaymentRejectedResponse),headers,HttpStatus.BAD_REQUEST);
		}else if (serObj.entrySet().iterator().hasNext() && serObj.entrySet().iterator().next().getKey().equals(Constants.PaymentAcceptedResponse)) {
			log.info("Valid Response");
			return new ResponseEntity<Object>((PaymentAcceptedResponse)serObj.get(Constants.PaymentAcceptedResponse),headers,HttpStatus.ACCEPTED);
		}
		
		
		return new ResponseEntity<Object>(new Object(),HttpStatus.BAD_REQUEST);
	}

}
