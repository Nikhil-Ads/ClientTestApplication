package com.newgen.example.service;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.newgen.example.dto.ChangePasswordDTO;
import com.newgen.example.dto.UserChangeFormDTO;
import com.newgen.example.dto.UserDTO;
import com.newgen.example.exception.CustomException;
import com.newgen.example.model.StatusResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl extends GeneralService implements UserService  {
	
	@Value("${ecm_next.url}${user_service}${user_add_user}")
	private String addUser_url;

	@Value("${ecm_next.url}${user_service}${user_change_password}")
	private String changePassword_url;
	
	@Value("${ecm_next.url}${user_service}${user_update_password}")
	private String updatePassword_url;
	
	@Value("${ecm_next.url}${user_service}${user_check_user}")
	private String checkUser_url;
	
	@Value("${ecm_next.url}${user_service}${user_forgot_password}")
	private String forgotPassword_url;
	
	@Override
	public ResponseEntity<StatusResponse> changePassword(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Path: EmailId cannot be empty") @Pattern(regexp = "[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\\\-]*[.])+[a-z]{2,}", message = "Path: EmailId is not a valid address") String email,
			@NotEmpty(message = "Request: Body cannot be empty") @Valid UserChangeFormDTO userChangeFormDTO) 
		throws CustomException{
		log.debug("Entering method: changePassword()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(changePassword_url+"/"+email);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org",org);
		headers.set("accessToken",token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<UserChangeFormDTO> request=new HttpEntity<>(userChangeFormDTO,headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.POST , request, StatusResponse.class);
	}
		
	@Override
	public ResponseEntity<UserDTO> getUser(String org, String email) {
		log.debug("Entering method: getUser()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(checkUser_url+"/"+email);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("Content-Type","application/json");
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, UserDTO.class);
	}

	@Override
	public ResponseEntity<StatusResponse> forgotPassword(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Path: EmailId cannot be empty") @Pattern(regexp = "[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\\\-]*[.])+[a-z]{2,}", message = "Path: EmailId is not a valid address") String email)
		throws CustomException{
		log.debug("Entering method: forgotPassword()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(forgotPassword_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org",org);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String body="{ \"emailId\":\""+email+"\" }";
		
		HttpEntity<String> request=new HttpEntity<>(body,headers);
		log.debug(request.getBody());
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.POST , request, StatusResponse.class);
	}

	@Override
	public ResponseEntity<StatusResponse> updatePassword(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Request: Body cannot be empty") @Valid ChangePasswordDTO changePasswordDTO) 
			throws CustomException{
		log.debug("Entering method: updatePassword()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(updatePassword_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org",org);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<ChangePasswordDTO> request=new HttpEntity<>(changePasswordDTO,headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.POST , request, StatusResponse.class);
	}
}
