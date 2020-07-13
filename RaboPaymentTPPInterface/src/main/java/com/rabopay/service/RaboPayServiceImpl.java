package com.rabopay.service;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.UUID;
import com.rabopay.model.PaymentAcceptedResponse;
import com.rabopay.model.PaymentInitiationRequest;
import com.rabopay.model.PaymentRejectedResponse;
import com.rabopay.util.Constants;

@Service
public class RaboPayServiceImpl implements RaboPayService {


	private static final Logger LOGGER = LoggerFactory.getLogger(RaboPayServiceImpl.class);

	@Override
	public synchronized HashMap<String, Object> tppValidator(PaymentInitiationRequest paymentInitiationReq) {
		// TODO Auto-generated method stub
		//201_PaymentAccepted
		//400_PaymentRejected
		//422_PaymentRejected
		//500_GeneralError
		LOGGER.info("##########RaboPayServiceImpl: reached tppValidator");
		HashMap<String,Object> validatorObj = new HashMap<>();
		LOGGER.info("Validating PaymentInitiationRequest");
		
		if(Double.parseDouble(paymentInitiationReq.getAmount())<0 ||
				StringUtils.isEmpty(paymentInitiationReq.getCreditorIBAN()) ||
				StringUtils.isEmpty(paymentInitiationReq.getCurrency())||
				StringUtils.isEmpty(paymentInitiationReq.getDebtorIBAN())||
				paymentInitiationReq.getCurrency().length()>3||
				paymentInitiationReq.getCurrency().matches("[0-9]+")) {
			LOGGER.info("PaymentInitiationRequest invalid");
			validatorObj.put(Constants.PaymentRejectedResponse,new PaymentRejectedResponse(String.valueOf(Constants.PaymentRejected_422),Constants.TransactionStatus.Rejected.toString(),Constants.ErrorReasonCode.INVALID_REQUEST.toString()));
			return validatorObj;
		}
		LOGGER.info("Validating DebtorIBAN");
		String intValsOfdebtorIban = paymentInitiationReq.getDebtorIBAN().replaceAll("[a-zA-Z]", "");
		LOGGER.info("intValsOfdebtorIban"+intValsOfdebtorIban);
		double amount = Double.parseDouble(paymentInitiationReq.getAmount());
		int debtorIbanSum=0;
		char[] charObj = intValsOfdebtorIban.toCharArray();
		for(Character eachChar: charObj) {
			debtorIbanSum = debtorIbanSum+Character.getNumericValue(eachChar);
		}
		LOGGER.info("debtorIbanSum"+debtorIbanSum);
		LOGGER.info("ModVal"+debtorIbanSum % paymentInitiationReq.getDebtorIBAN().length());
		if(amount > 0 && (debtorIbanSum % paymentInitiationReq.getDebtorIBAN().length() ==0)) {
			LOGGER.info("Invalid Request");
			validatorObj.put(Constants.PaymentRejectedResponse,new PaymentRejectedResponse(String.valueOf(Constants.PaymentRejected_422),Constants.TransactionStatus.Rejected.toString(),Constants.ErrorReasonCode.LIMIT_EXCEEDED.toString()));
		}else {
			LOGGER.info("Valid Request");
			
			UUID uuid = UUID.randomUUID();
			LOGGER.info(" UUID Generated > "+uuid.toString());
			validatorObj.put(Constants.PaymentAcceptedResponse,new PaymentAcceptedResponse(uuid.toString(),String.valueOf(Constants.PaymentAccepted_201)));
		}
		
		
		return validatorObj;
	}



}
