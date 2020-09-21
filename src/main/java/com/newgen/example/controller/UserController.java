package com.newgen.example.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.newgen.example.validation.InputValidator;

@RestController
@RequestMapping("/users")
public class UserController extends ExceptionThrower{
	
	private InputValidator inputValidator;
	
	private UserService userService;
	
	@Autowired
	public UserController(InputValidator inputValidator, UserService userService) {
		this.inputValidator=inputValidator;
		this.userService=userService;
	}
	
	@PostMapping("/changePassword/{email}")
	public ResponseEntity<StatusResponse> changePassword(
			@RequestHeader
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@RequestHeader
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
		StatusResponse entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else if(userService.getUser(org, email) == null)
				throwNoResultsFoundException();
		else {	entity=userService.changePassword(org,token,email,userChangeFormDTO);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<StatusResponse>(entity,HttpStatus.OK);
	}
	
	@GetMapping("/checkUser/{email}")
	public ResponseEntity<UserDTO>	checkUser(
			@RequestHeader
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@PathVariable(name = "email")
			@NotEmpty(message = "Path: EmailId cannot be empty")
			@Pattern(regexp="^[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}$",message = "Path: EmailId is not a valid address")
			String email) throws CustomException{
		UserDTO entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else {	entity=userService.getUser(org, email);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<UserDTO>(entity,HttpStatus.OK);				
	}
	
	@GetMapping("/forgotPassword/{email}")
	public ResponseEntity<StatusResponse> forgotPassword(
			@RequestHeader
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@PathVariable(name = "email")
			@NotEmpty(message = "Path: EmailId cannot be empty")
			@Pattern(regexp="^[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}$",message = "Path: EmailId is not a valid address")
			String email) throws CustomException{
		StatusResponse entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else if(userService.getUser(org, email) == null)
				throwNoResultsFoundException();
		else {	entity=userService.forgotPassword(org, email);
				if(entity == null)
					throwECMServiceException();}
		return new ResponseEntity<StatusResponse>(entity,HttpStatus.OK);		
	}
	
	@PostMapping("/updatePassword/{email}")
	public ResponseEntity<StatusResponse> updatePassword(
			@RequestHeader
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@RequestHeader
			@NotEmpty(message = "Header: accessToken cannot be empty")
			String token,
			@RequestBody
			@NotEmpty(message = "Request: Body cannot be empty")
			@Valid
			ChangePasswordDTO changePasswordDTO,
			@PathVariable(name = "email")
			@NotEmpty(message = "Path: EmailId cannot be empty")
			@Pattern(regexp="^[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}$",message = "Path: EmailId is not a valid address")
			String email) throws CustomException {
		StatusResponse entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else if(userService.getUser(org, email) == null)
				throwNoResultsFoundException();
		else {	entity=userService.updatePassword(org,token,email,changePasswordDTO);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<StatusResponse>(entity,HttpStatus.OK);
	}
	
}
