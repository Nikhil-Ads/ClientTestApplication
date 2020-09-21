package com.newgen.example.service;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Service;

import com.newgen.example.dto.ChangePasswordDTO;
import com.newgen.example.dto.UserChangeFormDTO;
import com.newgen.example.dto.UserDTO;
import com.newgen.example.exception.CustomException;
import com.newgen.example.model.StatusResponse;

@Service
public interface UserService {

	StatusResponse changePassword(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Path: EmailId cannot be empty") @Pattern(regexp = "^[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}$", message = "Path: EmailId is not a valid address") String email,
			@NotEmpty(message = "Request: Body cannot be empty") @Valid UserChangeFormDTO userChangeFormDTO) throws CustomException;

	UserDTO getUser(String org, String email);

	StatusResponse forgotPassword(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Path: EmailId cannot be empty") @Pattern(regexp = "^[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}$", message = "Path: EmailId is not a valid address") String email) throws CustomException;

	StatusResponse updatePassword(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Path: EmailId cannot be empty") @Pattern(regexp = "^[a-z0-9\\-_.]*[a-z0-9]+@([a-z0-9\\-]*[.])+[a-z]{2,}$", message = "Path: EmailId is not a valid address") String email,
			@NotEmpty(message = "Request: Body cannot be empty") @Valid ChangePasswordDTO changePasswordDTO) throws CustomException;
}
