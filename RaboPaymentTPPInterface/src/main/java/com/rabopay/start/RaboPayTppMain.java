package com.rabopay.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import com.rabopay.controller.RaboPayController;
import com.rabopay.security.SecurityFilter;


@ComponentScan({"com.rabopay.controller","com.rabopay.security","com.rabopay.service","com.rabopay.util"})
//@ComponentScan(basePackageClasses = RaboPayController.class)
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
