package com.qa.country_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CountryControllerExceptionHandler {
	
	@ExceptionHandler(value = { CountryNotFoundException.class })
	public ResponseEntity<String> userNotFoundExceptions(CountryNotFoundException unfe) {
		return new ResponseEntity<String>(unfe.getMessage(), HttpStatus.NOT_FOUND);
	}

}
