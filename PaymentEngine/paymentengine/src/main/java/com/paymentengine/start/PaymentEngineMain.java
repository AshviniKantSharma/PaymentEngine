package com.paymentengine.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;





/**PaymentEngineMain : Class responsible to start application as a SpringBootApp
 * @author Ashvini
 *
 */
@ComponentScan({"com.paymentengine.controller","com.paymentengine.service","com.paymentengine.util","com.paymentengine.model"})

@SpringBootApplication
public class PaymentEngineMain {

	public static void main(String[] args) {
		SpringApplication.run(PaymentEngineMain.class, args);
	}
	
	
}
