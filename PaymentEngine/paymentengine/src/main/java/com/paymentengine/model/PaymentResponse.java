package com.paymentengine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**PaymentResponse : Model class for Success Response of Payment
 * @author Ashvini
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
	


	private String orderId;
	
	private String status;
	
	private String promotionMessage;


}
