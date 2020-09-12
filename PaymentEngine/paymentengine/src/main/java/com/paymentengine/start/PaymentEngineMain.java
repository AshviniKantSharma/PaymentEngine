package com.paymentengine.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.paymentengine.model.Orders;




/**PaymentEngineMain : Class responsible to start application as a SpringBootApp
 * @author Ashvini
 *
 */
@ComponentScan({"com.paymentengine.controller","com.paymentengine.service","com.paymentengine.util"})

@SpringBootApplication
public class PaymentEngineMain {

	public static void main(String[] args) {
		SpringApplication.run(PaymentEngineMain.class, args);
	}
	
	@Bean
	  public Orders initOrders() {
	    return new Orders();
	  }
}
