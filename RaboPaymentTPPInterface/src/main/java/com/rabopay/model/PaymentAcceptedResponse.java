package com.rabopay.model;

public class PaymentAcceptedResponse {
	
	public PaymentAcceptedResponse(String paymentId, String status) {
		this.paymentId = paymentId;
		this.status = status;
	}

	private String paymentId;
	
	private String status;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
