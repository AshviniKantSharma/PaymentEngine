package com.rabopay.service;

import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.rabopay.model.PaymentAcceptedResponse;
import com.rabopay.model.PaymentInitiationRequest;
import com.rabopay.model.PaymentRejectedResponse;
import com.rabopay.util.Constants;

/**RaboPayService : Class to implement service level handling of TppValidator
 * @author Ashvini
 *
 */
@Slf4j
@Service
public class RaboPayServiceImpl implements RaboPayService {



	/* tppValidator : This method validates the submitted request & returns appropriate response
	 * @see com.rabopay.service.RaboPayService#tppValidator(com.rabopay.model.PaymentInitiationRequest)
	 */
	@Override
	public synchronized HashMap<String, Object> tppValidator(PaymentInitiationRequest paymentInitiationReq) throws NumberFormatException,Exception {
		// TODO Auto-generated method stub
		//201_PaymentAccepted
		//400_PaymentRejected
		//422_PaymentRejected
		//500_GeneralError
		log.info("##########RaboPayServiceImpl: reached tppValidator");
		HashMap<String,Object> validatorObj = new HashMap<>();
		log.info("Validating PaymentInitiationRequest");
		
		if(Double.parseDouble(paymentInitiationReq.getAmount())<0 ||
				StringUtils.isEmpty(paymentInitiationReq.getCreditorIBAN()) ||
				StringUtils.isEmpty(paymentInitiationReq.getCurrency())||
				StringUtils.isEmpty(paymentInitiationReq.getDebtorIBAN())||
				paymentInitiationReq.getCurrency().length()>3||
				paymentInitiationReq.getCurrency().matches("[0-9]+")) {
			log.info("PaymentInitiationRequest invalid");
			validatorObj.put(Constants.PaymentRejectedResponse,new PaymentRejectedResponse(String.valueOf(Constants.PaymentRejected_422),Constants.TransactionStatus.Rejected.toString(),Constants.ErrorReasonCode.INVALID_REQUEST.toString()));
			return validatorObj;
		}
		log.info("Validating DebtorIBAN");
		int debtorIbanSum= calculateIbanSumOfDebtor(paymentInitiationReq);
		double amount = Double.parseDouble(paymentInitiationReq.getAmount());
		log.info("debtorIbanSum"+debtorIbanSum);
		log.info("ModVal"+debtorIbanSum % paymentInitiationReq.getDebtorIBAN().length());
		if(amount > 0 && (debtorIbanSum % paymentInitiationReq.getDebtorIBAN().length() ==0)) {
			log.info("Invalid Request");
			validatorObj.put(Constants.PaymentRejectedResponse,new PaymentRejectedResponse(String.valueOf(Constants.PaymentRejected_422),Constants.TransactionStatus.Rejected.toString(),Constants.ErrorReasonCode.LIMIT_EXCEEDED.toString()));
		}else {
			log.info("Valid Request");
			
			String uuidGenerated = uuidGenerator();
			log.info(" UUID Generated > "+uuidGenerated);
			validatorObj.put(Constants.PaymentAcceptedResponse,new PaymentAcceptedResponse(uuidGenerated,String.valueOf(Constants.PaymentAccepted_201)));
		}
		
		
		return validatorObj;
	}
	
	
	
	@Override
	public int calculateIbanSumOfDebtor(PaymentInitiationRequest paymentInitiationReq) throws Exception{
		
		String intValsOfdebtorIban = paymentInitiationReq.getDebtorIBAN().replaceAll("[a-zA-Z]", "");
		log.info("intValsOfdebtorIban"+intValsOfdebtorIban);
		
		int debtorIbanSum=0;
		char[] charObj = intValsOfdebtorIban.toCharArray();
		for(Character eachChar: charObj) {
			debtorIbanSum = debtorIbanSum+Character.getNumericValue(eachChar);
		}
		
		return debtorIbanSum;
	}



}
