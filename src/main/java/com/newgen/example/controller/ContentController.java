/**
 * 
 */
package com.newgen.example.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
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

import com.newgen.example.dto.ContentDTO;
import com.newgen.example.exception.CustomException;
import com.newgen.example.service.ContentService;
import com.newgen.example.validation.InputValidator;

/**
 * @author Nikhil Adlakha
 *
 */
@RequestMapping("/contents")
public class ContentController extends GeneralController {
		
	private ContentService contentService;

	public ContentController(InputValidator inputValidator,ContentService contentService) {
		super(inputValidator);
		this.contentService=contentService;
	}
	
	@GetMapping()
	public ResponseEntity<List<ContentDTO>> getContents(
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
			@RequestHeader(required = false)
			String offset,
			@RequestHeader(required = false)
			String sortOn,
			@RequestHeader(required = false)
			String sortOrder,
			@RequestHeader(required = false)
			String limit,			
			@RequestParam(name = "count",required = false)
			String count,
			@RequestParam(name = "property",required = false)
			String property,
			@RequestParam(name = "order",required = false)
			String order) throws CustomException{
		List<ContentDTO> entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();			
		else {	entity=contentService.searchContents(org, tenantId, userId, token, count,property,order,offset,sortOn,sortOrder,limit);
				if(entity == null)
					throwECMServiceException();
		}
		return new ResponseEntity<List<ContentDTO>>(entity,HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createContent(
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
			String payLoad) throws CustomException {
		String entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();
		else {	entity=contentService.createContent(org, tenantId, userId, token, payLoad);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<String>(entity,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateContent(
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
			String payLoad,
			@PathVariable(name = "id")
			@NotEmpty(message="Path: id cannot be empty")
			String id,
			@RequestParam(name = "version",required = false)
			String version)
			throws CustomException{
		String entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();	
	    else {	entity=contentService.updateContent(org, tenantId, userId, token, payLoad,id,version);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<String>(entity,HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteContent(
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
			@NotEmpty(message="Path: id cannot be empty")
			String id,
			@RequestParam(name = "version",required = false)
			String version)
			throws CustomException{
		String entity=null;
		
		if(inputValidator.validateInput("header_org", org, "String", 5, false,"ECM"))
				throwInvalidOrgException();	
	    else {	entity=contentService.deleteContent(org, tenantId, userId, token, id,version);
				if(entity == null)
					throwECMServiceException();
			 }
		return new ResponseEntity<String>(entity,HttpStatus.OK);
		}
	
	
	

}
