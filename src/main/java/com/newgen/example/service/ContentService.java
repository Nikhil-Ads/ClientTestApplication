/**
 * 
 */
package com.newgen.example.service;

import javax.validation.constraints.NotEmpty;

import org.springframework.http.ResponseEntity;

/**
 * @author Nikhil Adlakha
 *
 */
public interface ContentService {

	ResponseEntity<String> searchContents(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: tenantId cannot be empty") String tenantId,
			@NotEmpty(message = "Header: userId cannot be empty") String userId,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token, 
			String count,String property, String order,
			String offset, String sortOn, String sortOrder, String limit);

	ResponseEntity<String> createContent(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: tenantId cannot be empty") String tenantId,
			@NotEmpty(message = "Header: userId cannot be empty") String userId,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Request: Body cannot be empty") String payLoad);

	ResponseEntity<String> updateContent(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: tenantId cannot be empty") String tenantId,
			@NotEmpty(message = "Header: userId cannot be empty") String userId,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Request: Body cannot be empty") String payLoad,
			@NotEmpty(message = "Path: id cannot be empty") String id, String version);

	ResponseEntity<String> deleteContent(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: tenantId cannot be empty") String tenantId,
			@NotEmpty(message = "Header: userId cannot be empty") String userId,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Path: id cannot be empty") String id, String version);
	
	
	

}
