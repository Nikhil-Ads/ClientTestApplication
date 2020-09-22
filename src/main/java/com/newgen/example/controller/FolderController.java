package com.newgen.example.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newgen.example.dto.FolderDTO;
import com.newgen.example.exception.CustomException;
import com.newgen.example.service.FolderService;

@RestController
@RequestMapping("/folders")
public class FolderController extends GeneralController{
	
	private FolderService folderService;

	public FolderController(FolderService folderService) {
		this.folderService=folderService;
		
	}
	
	@PostMapping
	public ResponseEntity<FolderDTO> createFolder(
			@RequestHeader(name="org")
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@RequestHeader(name="tenantId")
			@NotEmpty(message = "Header: tenantId cannot be empty")
			String tenantId,
			@RequestHeader(name="userId")
			@NotEmpty(message = "Header: userId cannot be empty")
			String userId,
			@RequestHeader(name="accessToken")
			@NotEmpty(message = "Header: accessToken cannot be empty")
			String token,
			@RequestBody
			@NotEmpty(message = "Request: Body cannot be empty")
			@Valid
			FolderDTO folderDTO) throws CustomException {
		ResponseEntity<FolderDTO> entity=folderService.createFolder(org, tenantId, userId, token, folderDTO);
		if(entity.getStatusCode().is5xxServerError())
					throwECMServiceException();			 
		return entity;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FolderDTO> updateFolder(
			@RequestHeader(name="org")
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@RequestHeader(name="tenantId")
			@NotEmpty(message = "Header: tenantId cannot be empty")
			String tenantId,
			@RequestHeader(name="userId")
			@NotEmpty(message = "Header: userId cannot be empty")
			String userId,
			@RequestHeader(name="accessToken")
			@NotEmpty(message = "Header: accessToken cannot be empty")
			String token,
			@RequestBody
			@NotEmpty(message = "Request: Body cannot be empty")
			@Valid
			FolderDTO folderDTO,
			@PathVariable(name = "id")
			@NotEmpty(message="Folder Id cannot be empty")
			String id,
			@RequestParam(name = "version",required = false)
			String version
			) throws CustomException {
		ResponseEntity<FolderDTO> entity=folderService.updateFolder(id,org, tenantId, userId, token, folderDTO,version);
		if(entity.getStatusCode().is5xxServerError())
					throwECMServiceException();			 
		return entity;
	}
	
	@GetMapping()
	public ResponseEntity<String> searchFolders(
			@RequestHeader(name="org")
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@RequestHeader(name="tenantId")
			@NotEmpty(message = "Header: tenantId cannot be empty")
			String tenantId,
			@RequestHeader(name="accessToken")
			@NotEmpty(message = "Header: accessToken cannot be empty")
			String token,
			@RequestParam(required = false)
			String query) throws CustomException{
		ResponseEntity<String> entity=folderService.searchFolders(org, tenantId, token, query);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();			 
		return entity;				
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFolder(
			@RequestHeader(name="org")
			@NotEmpty(message="Header: org cannot be empty")
			String org,
			@RequestHeader(name="tenantId")
			@NotEmpty(message = "Header: tenantId cannot be empty")
			String tenantId,
			@RequestHeader(name="userId")
			@NotEmpty(message = "Header: userId cannot be empty")
			String userId,
			@RequestHeader(name="accessToken")
			@NotEmpty(message = "Header: accessToken cannot be empty")
			String token,
			@PathVariable(name = "id")
			String id,
			@RequestParam(name="version",required = false)
			String version,
			@RequestParam(name="recursive",required=false)
			Boolean recursive,
			@RequestParam(name="isCabinet",required = false)
			Boolean isCabinet
			) throws CustomException{
		ResponseEntity<String> entity=folderService.deleteFolder(org, tenantId, userId, token, id,version,recursive,isCabinet);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();			 
		return entity;			
	}
}
