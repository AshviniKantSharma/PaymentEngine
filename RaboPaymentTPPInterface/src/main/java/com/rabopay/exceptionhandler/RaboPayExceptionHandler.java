package com.rabopay.exceptionhandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import com.rabopay.exceptionhandler.*;
import com.rabopay.exceptionhandler.NumberFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/** RaboPayExceptionHandler: Class for Handling exception
 * @author Ashvini
 *
 */
@ControllerAdvice
public class RaboPayExceptionHandler extends ResponseEntityExceptionHandler{
	
	 /** 
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	
	@ExceptionHandler(Exception.class)
    protected ResponseEntity <Object> handleCustomAPIException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiErrorResponse response = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withStatus(status)
            .withDetail("Something went wrong")
            .withMessage(ex.getLocalizedMessage())
            .withError_code("500")
            .withError_code(status.INTERNAL_SERVER_ERROR.name())
            .atTime(LocalDateTime.now(ZoneOffset.UTC))
            .build();
        return new ResponseEntity <> (response, status);
    }
	
	@ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity <Object> handleNumberFormatException(final NumberFormatException ex, WebRequest request) {

		 final String bodyOfResponse = "Check Your Inputs";
	        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
