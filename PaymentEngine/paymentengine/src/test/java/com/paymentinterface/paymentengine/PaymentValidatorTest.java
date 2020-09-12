package com.paymentinterface.paymentengine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.paymentengine.controller.PaymentController;
import com.paymentengine.model.PaymentInitiationRequest;
import com.paymentengine.model.PaymentResponse;
import com.paymentengine.service.PaymentService;
import com.paymentengine.util.Constants;


@ExtendWith(MockitoExtension.class)
public class PaymentValidatorTest {

	@InjectMocks
	PaymentController controllerObj;
	
	@Mock
	PaymentService payService;
	
	@Test
	public void testInitiateOrderPositive() {
		
		
		PaymentInitiationRequest payInitReq = new PaymentInitiationRequest();
		payInitReq.setOrderId("");
		payInitReq.setProductType(Constants.ProductTypes.Book.toString());
		
		PaymentResponse payResp = new PaymentResponse();
		payResp.setOrderId("b2c9a5ba-a0f0-4021-9ef4-6037a502fb23");
		payResp.setStatus(Constants.AcknowledgementMode.PackingSlip.toString());
		payResp.setPromotionMessage(Constants.OrderPromotion.None.toString());
		try {
			
			when(payService.facilitatePaymentAndOrder(payInitReq)).thenReturn(payResp);
			
			PaymentResponse payRespObj = controllerObj.initiatePayment(payInitReq);
			assertThat(payRespObj.getOrderId()).isNotEmpty();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testInitiateOrderNegative() {
		
		
		PaymentInitiationRequest payInitReq = new PaymentInitiationRequest();
		payInitReq.setOrderId("");
		payInitReq.setProductType("None");
		
		
		try {
			
			when(payService.facilitatePaymentAndOrder(payInitReq)).thenThrow(new Exception("UNsupported Product TYpe"));
			
			PaymentResponse payRespObj = controllerObj.initiatePayment(payInitReq);
			Assertions.assertThrows(Exception.class, () -> {
			
				controllerObj.initiatePayment(payInitReq);
			});
			//assertThat(payRespObj.getOrderId()).isNotEmpty();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
