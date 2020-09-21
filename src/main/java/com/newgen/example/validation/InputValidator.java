package com.newgen.example.validation;

import java.text.DateFormat;

import org.owasp.esapi.Validator;
import org.owasp.esapi.reference.DefaultValidator;
import org.springframework.stereotype.Component;

@Component
public class InputValidator {
	
	private Validator validator;
	
	public InputValidator() {
		validator=new DefaultValidator();
	}
	
	public boolean validateInput(String context, String input, String type, int maxLength, boolean allowNull, String value) {
		if(validator.isValidInput(context, input, type, maxLength, allowNull))
			return input.matches(value);
		return false;
	}

	public boolean validateDateInput(String context, String input, DateFormat format, boolean allowNull) {
		return validator.isValidDate(context, input, format, allowNull);
	}
}
