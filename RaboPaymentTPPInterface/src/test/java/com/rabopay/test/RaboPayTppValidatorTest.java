package com.rabopay.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rabopay.controller.RaboPayController;
import com.rabopay.model.PaymentAcceptedResponse;
import com.rabopay.model.PaymentInitiationRequest;
import com.rabopay.service.RaboPayService;
import com.rabopay.util.Constants;

@ExtendWith(MockitoExtension.class)
public class RaboPayTppValidatorTest {

	@InjectMocks
	RaboPayController controllerObj;
	
	@Mock
	RaboPayService raboPayService;
	
	@Test
	public void testTppValidator() {
		
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Content-Type", "application/json");
		request.addHeader("X-Request-Id", "12345");
		request.addHeader("Signature", "XvzTqQbwDjOh+DPOiC7X35cr71a7uZIw4RX88D8bfmOKHx/OPJ8TWo52Rv/KMBmoY1bOOUg/ScDN  B+vF0zIM3DHuz0u9MUj44MIT/SDiL2qbUh2Vo8QjclaZdO2mdZQcM8yLIqj7C05kc+KQ7oxDGRYV  VuB8sBuJ5rFzcfkpqbE=");
		request.addHeader("Signature-Certificate", "Sandbox-TPPcSedxGR5FbPU/3bLdFwxHQ9K8dcCyEqvR7r4RHV1cFENJf8iAh64kDIHSp4bFUteea05EjjqOZo1  ARmzZXCx+oEZHEdGcO8NHnbkXIwDoWSeF/pcKU2z7PSo1Yfk9XYUZaoWIAOsPQst2gUp94dlcUrg  sj4ebIeW/rbH+iiHjNg=");
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		PaymentInitiationRequest payInitReq = new PaymentInitiationRequest();
		payInitReq.setDebtorIBAN("abc123ABC45");
		payInitReq.setCreditorIBAN("bcd12345");
		payInitReq.setCurrency("EUR");
		payInitReq.setAmount("12.0");
		try {
			HashMap<String,Object> validatorObj = new HashMap<>();
			validatorObj.put(Constants.PaymentAcceptedResponse,new PaymentAcceptedResponse("18c161d1-a13a-493c-b0e5-29ab02dac0f6",String.valueOf(Constants.PaymentAccepted_201)));
			
			when(raboPayService.tppValidator(payInitReq)).thenReturn(validatorObj);
			
			ResponseEntity<Object> initiatePayment = controllerObj.initiatePayment(payInitReq, request);
			assertThat(initiatePayment.getStatusCodeValue()).isEqualTo(202);
			System.out.println(initiatePayment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTppCertificate () {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Content-Type", "application/json");
		request.addHeader("X-Request-Id", "12345");
		request.addHeader("Signature", "XvzTqQbwDjOh+DPOiC7X35cr71a7uZIw4RX88D8bfmOKHx/OPJ8TWo52Rv/KMBmoY1bOOUg/ScDN  B+vF0zIM3DHuz0u9MUj44MIT/SDiL2qbUh2Vo8QjclaZdO2mdZQcM8yLIqj7C05kc+KQ7oxDGRYV  VuB8sBuJ5rFzcfkpqbE=");
		request.addHeader("Signature-Certificate", "Sandbox-TPPcSedxGR5FbPU/3bLdFwxHQ9K8dcCyEqvR7r4RHV1cFENJf8iAh64kDIHSp4bFUteea05EjjqOZo1  ARmzZXCx+oEZHEdGcO8NHnbkXIwDoWSeF/pcKU2z7PSo1Yfk9XYUZaoWIAOsPQst2gUp94dlcUrg  sj4ebIeW/rbH+iiHjNg=");
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		PaymentInitiationRequest payInitReq = new PaymentInitiationRequest();
		payInitReq.setDebtorIBAN("abc123ABC45");
		payInitReq.setCreditorIBAN("bcd12345");
		payInitReq.setCurrency("EUR");
		payInitReq.setAmount("12.0");
		try {
			HashMap<String,Object> validatorObj = new HashMap<>();
			validatorObj.put(Constants.PaymentAcceptedResponse,new PaymentAcceptedResponse("18c161d1-a13a-493c-b0e5-29ab02dac0f6",String.valueOf(Constants.PaymentAccepted_201)));
			
			when(raboPayService.tppValidator(payInitReq)).thenReturn(validatorObj);
			
			ResponseEntity<Object> initiatePayment = controllerObj.initiatePayment(payInitReq, request);
			assertThat(initiatePayment.getStatusCodeValue()).isEqualTo(202);
			System.out.println(initiatePayment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
