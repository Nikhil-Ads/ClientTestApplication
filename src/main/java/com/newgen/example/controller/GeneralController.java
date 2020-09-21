package com.newgen.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.newgen.example.validation.InputValidator;

@RestController
public abstract class GeneralController extends ExceptionThrower {

	protected final InputValidator inputValidator;
	
	@Autowired
	public GeneralController(InputValidator inputValidator) {
		this.inputValidator=inputValidator;
	}
	
}
