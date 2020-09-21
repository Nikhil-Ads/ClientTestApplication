package com.newgen.example.service;

import org.springframework.stereotype.Service;

import com.newgen.example.model.LoginForm;
import com.newgen.example.model.StatusResponse;
import com.newgen.example.model.TokenResponse;

@Service
public interface SessionService {

	String 			login(String org, LoginForm loginForm);
	
	StatusResponse 	logout(String org, String token);

	TokenResponse 	getTokens(String org, String token, String refreshToken);
	
	String			validateToken(String org, String token);
}
