package com.newgen.example.validation;

import org.springframework.validation.Errors;
import com.newgen.example.error.constants.CommonErrorConstants;
import com.newgen.example.exception.CustomException;
import com.newgen.example.exception.CustomExceptionMessage;

public class ValidationErrorBuilder {// NOSONAR

	public static CustomExceptionMessage fromBindingErrors(Errors errors) throws CustomException {
		return new CustomExceptionMessage(
				Integer.parseInt(CommonErrorConstants.PREFIX + CommonErrorConstants.CONSTRAINT_VIOLATION),
				errors.getAllErrors().get(0).getDefaultMessage());
	}
	
	public static CustomExceptionMessage fromViolationErrors(Exception exception) throws CustomException {
		return new CustomExceptionMessage(
				Integer.parseInt(CommonErrorConstants.PREFIX + CommonErrorConstants.CONSTRAINT_VIOLATION),
				exception.getMessage());
	}
	
}
/*
 * ValidationError error = new ValidationError("Validation failed. " +
 * errors.getErrorCount() + " error(s)"); for (ObjectError objectError :
 * errors.getAllErrors()) {
 * error.addValidationError(objectError.getDefaultMessage()); } return error;
 */
