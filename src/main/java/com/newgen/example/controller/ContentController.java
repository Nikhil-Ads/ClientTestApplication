/**
 * 
 */
package com.newgen.example.controller;

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

import com.newgen.example.exception.CustomException;
import com.newgen.example.service.ContentService;

/**
 * @author Nikhil Adlakha
 *
 */

@RestController
@RequestMapping("/contents")
public class ContentController extends GeneralController {
		
	
	private ContentService contentService;

	public ContentController(ContentService contentService) {
		this.contentService=contentService;
	}
	
	@GetMapping()
	public ResponseEntity<String> getContents(
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
			@RequestHeader(name="offset",required = false)
			String offset,
			@RequestHeader(name="sortOn",required = false)
			String sortOn,
			@RequestHeader(name="sortOrder",required = false)
			String sortOrder,
			@RequestHeader(name="limit",required = false)
			String limit,			
			@RequestParam(name = "count",required = false)
			String count,
			@RequestParam(name = "property",required = false)
			String property,
			@RequestParam(name = "order",required = false)
			String order) throws CustomException{
		ResponseEntity<String> entity=contentService.searchContents(org, tenantId, userId, token, count,property,order,offset,sortOn,sortOrder,limit);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();			 
		return entity;
	}

	@PostMapping
	public ResponseEntity<String> createContent(
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
			String payLoad) throws CustomException {
		ResponseEntity<String> entity=contentService.createContent(org, tenantId, userId, token, payLoad);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();			 
		return entity;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateContent(
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
			String payLoad,
			@PathVariable(name = "id")
			@NotEmpty(message="Path: id cannot be empty")
			String id,
			@RequestParam(name = "version",required = false)
			String version)
			throws CustomException{
		ResponseEntity<String> entity=contentService.updateContent(org, tenantId, userId, token, payLoad,id,version);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();			 
		return entity;	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteContent(
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
			@NotEmpty(message="Path: id cannot be empty")
			String id,
			@RequestParam(name = "version",required = false)
			String version)
			throws CustomException{
		ResponseEntity<String> entity=contentService.deleteContent(org, tenantId, userId, token, id,version);
		if(entity.getStatusCode().is5xxServerError())
			throwECMServiceException();			 
		return entity;
		}
	
	
	

}
