package com.newgen.example.service;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.newgen.example.dto.ChangePasswordDTO;
import com.newgen.example.dto.UserChangeFormDTO;
import com.newgen.example.dto.UserDTO;
import com.newgen.example.exception.CustomException;
import com.newgen.example.model.StatusResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl extends GeneralService implements UserService  {
	
	@Value("${ecm_next.url}+${user_service}+${user_add_user}")
	private String addUser_url;

	@Value("${ecm_next.url}+${user_service}+${user_change_password}")
	private String changePassword_url;
	
	@Value("${ecm_next.url}+${user_service}+${user_change_password}")
	private String updatePassword_url;
	
	@Value("${ecm_next.url}+${user_service}+${user_check_user}")
	private String checkUser_url;
	
	@Value("${ecm_next.url}+${user_service}+${user_forgot_password}")
	private String forgotPassword_url;
	
	@Override
	public StatusResponse changePassword(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Path: EmailId cannot be empty") @Pattern(regexp = "[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\\\-]*[.])+[a-z]{2,}", message = "Path: EmailId is not a valid address") String email,
			@NotEmpty(message = "Request: Body cannot be empty") @Valid UserChangeFormDTO userChangeFormDTO) 
		throws CustomException{
		log.debug("Entering method: changePassword()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(changePassword_url+"/"+email);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org",org);
		headers.set("accessToken",token);
		
		HttpEntity<UserChangeFormDTO> request=new HttpEntity<>(userChangeFormDTO,headers);
		
		ResponseEntity<StatusResponse> response=restTemplate.exchange(builder.toUriString(), HttpMethod.POST , request, StatusResponse.class);
		
		HttpStatus status=response.getStatusCode();
		if(status.is4xxClientError()) { 
				throwCustomException(response.getBody().toString(), status);
				return null;}
		else if(status.is5xxServerError())
				return null;
		else 	return response.getBody();
	}
		
	@Override
	public UserDTO getUser(String org, String email) {
		log.debug("Entering method: getUser()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(checkUser_url+"/"+email);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("Content-Type","application/json");
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		ResponseEntity<UserDTO> response=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, UserDTO.class);
		
		if(response.getStatusCode().is4xxClientError())
				return null;
		else	return response.getBody();
	}

	@Override
	public StatusResponse forgotPassword(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Path: EmailId cannot be empty") @Pattern(regexp = "[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\\\-]*[.])+[a-z]{2,}", message = "Path: EmailId is not a valid address") String email)
		throws CustomException{
		log.debug("Entering method: changePassword()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(forgotPassword_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org",org);
		
		HttpEntity<String> request=new HttpEntity<>(email,headers);
		log.debug(request.getBody());
		
		ResponseEntity<StatusResponse> response=restTemplate.exchange(builder.toUriString(), HttpMethod.POST , request, StatusResponse.class);
		
		HttpStatus status=response.getStatusCode();
		if(status.is4xxClientError()) { 
				throwCustomException(response.getBody().toString(), status);
				return null;}
		else if(status.is5xxServerError())
				return null;
		else 	return response.getBody();
	}

	@Override
	public StatusResponse updatePassword(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Path: EmailId cannot be empty") @Pattern(regexp = "^[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}$", message = "Path: EmailId is not a valid address") String email,
			@NotEmpty(message = "Request: Body cannot be empty") @Valid ChangePasswordDTO changePasswordDTO) 
			throws CustomException{
		log.debug("Entering method: updatePassword()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(changePassword_url+"/"+email);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org",org);
		headers.set("accessToken",token);
		
		HttpEntity<ChangePasswordDTO> request=new HttpEntity<>(changePasswordDTO,headers);
		
		ResponseEntity<StatusResponse> response=restTemplate.exchange(builder.toUriString(), HttpMethod.POST , request, StatusResponse.class);
		
		HttpStatus status=response.getStatusCode();
		if(status.is4xxClientError()) { 
				throwCustomException(response.getBody().toString(), status);
				return null;}
		else if(status.is5xxServerError())
				return null;
		else 	return response.getBody();
	}
}
