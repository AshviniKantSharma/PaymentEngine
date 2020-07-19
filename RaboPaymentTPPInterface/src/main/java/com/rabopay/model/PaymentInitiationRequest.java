package com.rabopay.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/** PaymentInitiationRequest : Model Class for the Payment Request 
 * @author Ashvini
 *
 */
@Data
@NoArgsConstructor
public class PaymentInitiationRequest {
	
	private String debtorIBAN;
	
	private String creditorIBAN;
	
	private String amount;
	
	private String endToEndId;
	
	private String currency;


}
