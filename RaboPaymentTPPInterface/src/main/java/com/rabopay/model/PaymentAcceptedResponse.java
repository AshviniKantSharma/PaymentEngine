package com.rabopay.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**PaymentAcceptedResponse : Model class for Success Response of Payment
 * @author Ashvini
 *
 */
@Data
@AllArgsConstructor
public class PaymentAcceptedResponse {
	

	private String paymentId;
	
	private String status;


}
