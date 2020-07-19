package com.rabopay.exceptionhandler;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NumberFormatException extends RuntimeException {
    public NumberFormatException() {
        super();
    }
    public NumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }
    public NumberFormatException(String message) {
        super(message);
    }
    public NumberFormatException(Throwable cause) {
        super(cause);
    }
}
