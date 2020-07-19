package com.rabopay.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.rabopay.security.SecurityFilter;


/**RaboPayTppMain : Class responsible to start application as a SpringBootApp
 * @author Ashvini
 *
 */
@ComponentScan({"com.rabopay.controller","com.rabopay.security","com.rabopay.service","com.rabopay.util","com.rabopay.exceptionhandler"})
@PropertySource("classpath:application.properties")
@SpringBootApplication
public class RaboPayTppMain {

	public static void main(String[] args) {
		SpringApplication.run(RaboPayTppMain.class, args);
	}
	
	@Bean
	  public SecurityFilter securityFilter() {
	    return new SecurityFilter();
	  }
}
