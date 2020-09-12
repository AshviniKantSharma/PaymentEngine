package com.paymentengine.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**PaymentResponse : Model class for Success Response of Payment
 * @author Ashvini
 *
 */
@Data
@AllArgsConstructor
public class PaymentResponse {
	

	private String orderId;
	
	private String status;
	
	private String paymentId;
	
	private String promotionMessage;


}
