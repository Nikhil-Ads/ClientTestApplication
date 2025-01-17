package com.newgen.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.newgen.example.exception.CustomException;
import com.newgen.example.model.ExceptionResponse;

@ControllerAdvice
public class ErrorHandlingController {
	
	private final Logger logger = LoggerFactory.getLogger(ErrorHandlingController.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> generalException(Exception e){
		ExceptionResponse eR = new ExceptionResponse();
		eR.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		eR.setMessage(e.getMessage());
		logger.debug(e.getMessage());
		return new ResponseEntity<>(eR,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> invalidInputException(CustomException e){
		ExceptionResponse eR = new ExceptionResponse();
		eR.setCode(e.getCode());
		eR.setMessage(e.getMessage());
		logger.debug(e.getMessage());
		return new ResponseEntity<>(eR,e.getHttpStatus());
	}
}
