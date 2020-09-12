package com.paymentengine.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/** PaymentInitiationRequest : Model Class for the Payment Request 
 * @author Ashvini
 *
 */
@Data
@NoArgsConstructor
public class PaymentInitiationRequest {
	
	private String paymentId;
	
	private String paymentType;


}
