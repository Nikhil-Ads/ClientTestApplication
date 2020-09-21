package com.newgen.example.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newgen.example.dto.FolderDTO;
import com.newgen.example.exception.CustomException;
import com.newgen.example.service.FolderService;
import com.newgen.example.validation.InputValidator;

@RequestMapping("/folders")
public class FolderController extends GeneralController{
	
	private FolderService folderService;

	public FolderController(InputValidator inputValidator,FolderService folderService) {
		super(inputValidator);
		this.folderService=folderService;
	}
	
	@PostMapping
	public ResponseEntity<FolderDTO> createFolder(
			@RequestHeader
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@RequestHeader
			@NotEmpty(message = "Header: tenantId cannot be empty")
			String tenantId,
			@RequestHeader
			@NotEmpty(message = "Header: userId cannot be empty")
			String userId,
			@RequestHeader
			@NotEmpty(message = "Header: accessToken cannot be empty")
			String token,
			@RequestBody
			@NotEmpty(message = "Request: Body cannot be empty")
			@Valid
			FolderDTO folderDTO) throws CustomException {
		FolderDTO entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else {	entity=folderService.createFolder(org, tenantId, userId, token, folderDTO);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<FolderDTO>(entity,HttpStatus.OK);
	}
	
	@GetMapping("{query}")
	public ResponseEntity<List<FolderDTO>> searchFolders(
			@RequestHeader
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@RequestHeader
			@NotEmpty(message = "Header: tenantId cannot be empty")
			String tenantId,
			@RequestHeader
			@NotEmpty(message = "Header: userId cannot be empty")
			String userId,
			@RequestHeader
			@NotEmpty(message = "Header: accessToken cannot be empty")
			String token,
			@PathVariable(name = "query")
			String query) throws CustomException{
		List<FolderDTO> entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else {	entity=folderService.searchFolders(org, tenantId, userId, token, query);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<List<FolderDTO>>(entity,HttpStatus.OK);				
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFolder(
			@RequestHeader
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@RequestHeader
			@NotEmpty(message = "Header: tenantId cannot be empty")
			String tenantId,
			@RequestHeader
			@NotEmpty(message = "Header: userId cannot be empty")
			String userId,
			@RequestHeader
			@NotEmpty(message = "Header: accessToken cannot be empty")
			String token,
			@PathVariable(name = "id")
			String id) throws CustomException{
		String entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else {	entity=folderService.deleteFolder(org, tenantId, userId, token, id);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<String>(entity,HttpStatus.OK);			
	}
}
