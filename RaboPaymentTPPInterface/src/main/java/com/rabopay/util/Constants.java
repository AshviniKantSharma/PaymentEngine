package com.rabopay.util;

public interface Constants {
	//201_PaymentAccepted
	//400_PaymentRejected
	//422_PaymentRejected
	//500_GeneralError
	
	public static int ERROR_STATUS_500 = 500;
	
	public static int PaymentRejected_400 = 400;
	
	public static int PaymentRejected_422 = 422;
	
	public static int PaymentAccepted_201 = 201;
	
	public static String PaymentRejectedResponse = "PaymentRejectedResponse";
	
	public static String PaymentAcceptedResponse = "PaymentAcceptedResponse";
	
	
	public static enum ErrorReasonCode {
		UNKNOWN_CERTIFICATE,INVALID_SIGNATURE,INVALID_REQUEST,LIMIT_EXCEEDED,GENERAL_ERROR
	}
	
	public static enum TransactionStatus {
		Accepted,Rejected
	}
	
	

}
