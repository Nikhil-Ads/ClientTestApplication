package com.newgen.example.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newgen.example.dto.ChangePasswordDTO;
import com.newgen.example.dto.UserChangeFormDTO;
import com.newgen.example.dto.UserDTO;
import com.newgen.example.exception.CustomException;
import com.newgen.example.model.StatusResponse;
import com.newgen.example.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController extends ExceptionThrower{
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@PostMapping("/changePassword/{email}")
	public ResponseEntity<StatusResponse> changePassword(
			@RequestHeader(name="org")
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@RequestHeader(name="accessToken")
			@NotEmpty(message = "Header: accessToken cannot be empty")
			String token,
			@RequestBody
			@NotEmpty(message = "Request: Body cannot be empty")
			@Valid
			UserChangeFormDTO userChangeFormDTO,
			@PathVariable(name = "email")
			@NotEmpty(message = "Path: EmailId cannot be empty")
			@Pattern(regexp="^[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}$",message = "Path: EmailId is not a valid address")
			String email) throws CustomException {
		ResponseEntity<StatusResponse> entity=userService.changePassword(org,token,email,userChangeFormDTO);
		if(entity.getStatusCode().is5xxServerError())
				throwECMServiceException();		
		return entity;
	}
	
	@GetMapping("/checkUser/{email}")
	public ResponseEntity<UserDTO>	checkUser(
			@RequestHeader(name="org")
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@PathVariable(name = "email")
			@NotEmpty(message = "Path: EmailId cannot be empty")
			@Pattern(regexp="^[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}$",message = "Path: EmailId is not a valid address")
			String email) throws CustomException{
		ResponseEntity<UserDTO> entity=userService.getUser(org, email);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();		
		return entity;				
	}
	
	@GetMapping("/forgotPassword/{email}")
	public ResponseEntity<StatusResponse> forgotPassword(
			@RequestHeader(name="org")
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@PathVariable(name = "email")
			@NotEmpty(message = "Path: EmailId cannot be empty")
			@Pattern(regexp="^[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}$",message = "Path: EmailId is not a valid address")
			String email) throws CustomException{
		ResponseEntity<StatusResponse> entity=userService.forgotPassword(org, email);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();		
		return entity;		
	}
	
	@PostMapping("/updatePassword")
	public ResponseEntity<StatusResponse> updatePassword(
			@RequestHeader(name="org")
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@RequestBody
			@NotEmpty(message = "Request: Body cannot be empty")
			@Valid
			ChangePasswordDTO changePasswordDTO) throws CustomException {
		ResponseEntity<StatusResponse> entity=userService.updatePassword(org,changePasswordDTO);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();		
		return entity;
	}
	
}
