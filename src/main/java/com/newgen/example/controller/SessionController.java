package com.newgen.example.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newgen.example.exception.CustomException;
import com.newgen.example.model.LoginForm;
import com.newgen.example.model.StatusResponse;
import com.newgen.example.model.TokenResponse;
import com.newgen.example.service.SessionService;
import com.newgen.example.validation.InputValidator;

@RestController
@RequestMapping("/session")
public class SessionController extends ExceptionThrower {
	
	private InputValidator inputValidator;
	
	private SessionService loginService;
	
	@Autowired
	public SessionController(InputValidator inputValidator, SessionService loginService) {
		this.inputValidator=inputValidator;
		this.loginService=loginService;
	}

	@PostMapping("/login")
	public ResponseEntity<String> userLogin(
			@RequestHeader
			@NotEmpty(message="Header: ORG must not be empty")
			String org,
			@NotNull(message = "Request body cannot be empty")
			@Valid
			LoginForm loginForm) throws CustomException {		
		String entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else {	entity=loginService.login(org, loginForm);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<String>(entity,HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<StatusResponse> userLogout(
			@RequestHeader
			@NotEmpty(message="Header: ORG must not be empty")
			String org,
			@RequestHeader
			@NotNull(message = "Request body cannot be empty")
			String token
			) throws CustomException {		
		StatusResponse entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else {	entity=loginService.logout(org, token);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<StatusResponse>(entity,HttpStatus.OK);
	}
	
	@GetMapping("/tokens/get")
	public ResponseEntity<TokenResponse> getTokens(
			@RequestHeader
			@NotEmpty(message="Header: ORG must not be empty")
			String org,
			@RequestHeader
			@NotNull(message = "Access token cannot be empty")
			String token,
			@RequestHeader
			@NotNull(message = "refresh Token cannot be empty")
			String refreshToken) throws CustomException {		
		TokenResponse entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else {	entity=loginService.getTokens(org, token, refreshToken);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<TokenResponse>(entity,HttpStatus.OK);
	}
	
	@GetMapping("/tokens/validate")
	public ResponseEntity<String> validateToken(
			@RequestHeader
			@NotEmpty(message="Header: ORG must not be empty")
			String org,
			@RequestHeader
			@NotNull(message = "Access token cannot be empty")
			String token) throws CustomException {		
		String entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else {	entity=loginService.validateToken(org, token);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<String>(entity,HttpStatus.OK);
	}
}
