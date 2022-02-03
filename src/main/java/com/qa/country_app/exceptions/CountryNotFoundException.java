package com.qa.country_app.exceptions;

import javax.persistence.EntityNotFoundException;

public class CountryNotFoundException extends EntityNotFoundException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CountryNotFoundException() {
		super();
	}

	public CountryNotFoundException(String message) {
		super(message);
	}

}
