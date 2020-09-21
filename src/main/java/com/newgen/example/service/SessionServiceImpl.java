package com.newgen.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.newgen.example.model.LoginForm;
import com.newgen.example.model.StatusResponse;
import com.newgen.example.model.TokenResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionServiceImpl implements SessionService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${ecm_next.url}+${user_service}+${user_login}")
	private String login_url; 
	
	@Value("${ecm_next.url}+${user_service}+${user_logout}")
	private String logout_url;

	@Value("${ecm_next.url}+${user_service}+${user_get_token}")
	private String getTokens_url;

	@Value("${ecm_next.url}+${user_service}+${user_validate_token}")
	private String validateToken_url;
	
	@Override
	public String login(String org, LoginForm loginForm){
		log.debug("Entering Method: login()");
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(login_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		
		HttpEntity<String> request=new HttpEntity<>(loginForm.toString(), headers);
		
		ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, request, String.class);
		
		if(response.getStatusCode().is5xxServerError())
				return null;
		else	return response.getBody();
	}

	@Override
	public StatusResponse logout(String org, String token){
		log.debug("Entering method: logout()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(logout_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("Content-Type", "application/json");
		headers.set("accessToken", token);
		
		HttpEntity<String> request=new HttpEntity<String>(headers);
		
		ResponseEntity<StatusResponse> response=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, StatusResponse.class);
		
		if(response.getStatusCode().is5xxServerError())
				return null;
		else	return response.getBody();
	}
	
	@Override
	public TokenResponse getTokens(String org, String token, String refreshToken) {
		log.debug("Entering method: getTokens()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(getTokens_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("accessToken", token);
		headers.set("refreshToken", refreshToken);
		
		HttpEntity<String> request=new HttpEntity<String>(headers);
		
		ResponseEntity<TokenResponse> response=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, TokenResponse.class);
		
		if(response.getStatusCode().is5xxServerError())
				return null;
		else	return response.getBody();				
	}

	@Override
	public String validateToken(String org, String token) {
		log.debug("Entering method: validateToken()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(getTokens_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("Content-Type", "application/json");
		headers.set("accessToken", token);
		
		HttpEntity<String> request=new HttpEntity<String>(headers);
		
		ResponseEntity<String> response=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);
		
		if(response.getStatusCode().is5xxServerError())
				return null;
		else	return response.getBody();	
	}

}
