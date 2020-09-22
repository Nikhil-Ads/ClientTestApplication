package com.newgen.example.service;

import org.springframework.http.ResponseEntity;

import com.newgen.example.model.LoginForm;
import com.newgen.example.model.StatusResponse;
import com.newgen.example.model.TokenResponse;

public interface SessionService {

	ResponseEntity<String> 		login(String org, LoginForm form);
	
	ResponseEntity<StatusResponse> 	logout(String org, String token);

	ResponseEntity<TokenResponse> 	getTokens(String org, String token, String refreshToken);
	
	ResponseEntity<String>			validateToken(String org, String token);
}
