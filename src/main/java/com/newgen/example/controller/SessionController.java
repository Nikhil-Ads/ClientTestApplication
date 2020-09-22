package com.newgen.example.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newgen.example.exception.CustomException;
import com.newgen.example.model.LoginForm;
import com.newgen.example.model.StatusResponse;
import com.newgen.example.model.TokenResponse;
import com.newgen.example.service.SessionService;

@RestController
@RequestMapping("/session")
public class SessionController extends ExceptionThrower {
	
	private SessionService loginService;
	
	@Autowired
	public SessionController(SessionService loginService) {
		this.loginService=loginService;
	}

	@PostMapping("/login")
	public ResponseEntity<String> userLogin(
			@RequestHeader(name = "org")
			@NotEmpty(message="Header: ORG must not be empty")
			String org,
			@RequestBody
			@NotNull(message = "Request body cannot be empty")
			@Valid
			LoginForm form) throws CustomException {		
		ResponseEntity<String> entity=loginService.login(org, form);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();							 
		return entity;
	}
	
	@GetMapping("/logout")
	public ResponseEntity<StatusResponse> userLogout(
			@RequestHeader(name = "org")
			@NotEmpty(message="Header: ORG must not be empty")
			String org,
			@RequestHeader(name= "accessToken")
			@NotNull(message = "Request body cannot be empty")
			String token
			) throws CustomException {		
		ResponseEntity<StatusResponse> entity=loginService.logout(org, token);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();							 
		return entity;
	}
	
	@GetMapping("/tokens/get")
	public ResponseEntity<TokenResponse> getTokens(
			@RequestHeader(name = "org")
			@NotEmpty(message="Header: ORG must not be empty")
			String org,
			@RequestHeader(name = "accessToken")
			@NotNull(message = "Access token cannot be empty")
			String token,
			@RequestHeader(name = "refreshToken")
			@NotNull(message = "refresh Token cannot be empty")
			String refreshToken) throws CustomException {		
		ResponseEntity<TokenResponse> entity=loginService.getTokens(org, token, refreshToken);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();							 
		return entity;
	}
	
	@GetMapping("/tokens/validate")
	public ResponseEntity<String> validateToken(
			@RequestHeader
			@NotEmpty(message="Header: ORG must not be empty")
			String org,
			@RequestHeader(name="accessToken")
			@NotNull(message = "Access token cannot be empty")
			String token) throws CustomException {		
		ResponseEntity<String> entity=loginService.validateToken(org, token);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();							 
		return entity;
	}
}
