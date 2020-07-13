package com.rabopay.model;

public class PaymentRejectedResponse {
	
	public PaymentRejectedResponse(String status,String reason,String reasonCode){
		this.status = status;
		this.reason = reason;
		this.reasonCode = reasonCode;
	}
	
	private String status;
	
	private String reason;
	
	private String reasonCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	

}
