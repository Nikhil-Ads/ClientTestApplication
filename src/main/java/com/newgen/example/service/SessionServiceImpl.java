package com.newgen.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.newgen.example.model.LoginForm;
import com.newgen.example.model.StatusResponse;
import com.newgen.example.model.TokenResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SessionServiceImpl extends GeneralService implements SessionService{
	
	@Value("${ecm_next.url}${user_service}${user_login}")
	private String login_url; 
	
	@Value("${ecm_next.url}${user_service}${user_logout}")
	private String logout_url;

	@Value("${ecm_next.url}${user_service}${user_get_token}")
	private String getTokens_url;

	@Value("${ecm_next.url}${user_service}${user_validate_token}")
	private String validateToken_url;
	
	@Override
	public ResponseEntity<String> login(String org, LoginForm form){
		log.debug("Entering Method: login()");
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(login_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<LoginForm> request=new HttpEntity<>(form, headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, request, String.class);
	}

	@Override
	public ResponseEntity<StatusResponse> logout(String org, String token){
		log.debug("Entering method: logout()");
		
		log.info(logout_url);
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(logout_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("accessToken", token);
		
		HttpEntity<String> request=new HttpEntity<String>(headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, StatusResponse.class);
	}
	
	@Override
	public ResponseEntity<TokenResponse> getTokens(String org, String token, String refreshToken) {
		log.debug("Entering method: getTokens()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(getTokens_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("accessToken", token);
		headers.set("refreshToken", refreshToken);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request=new HttpEntity<String>(headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, TokenResponse.class);				
	}

	@Override
	public ResponseEntity<String> validateToken(String org, String token) {
		log.debug("Entering method: validateToken()");
		
		log.debug(validateToken_url);
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(validateToken_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("accessToken", token);
		
		HttpEntity<String> request=new HttpEntity<String>(headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);	
	}

}
