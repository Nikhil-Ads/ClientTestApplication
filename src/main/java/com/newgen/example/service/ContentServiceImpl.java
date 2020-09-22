/**
 * 
 */
package com.newgen.example.service;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Nikhil Adlakha
 *
 */
@Service
@Slf4j
public class ContentServiceImpl extends GeneralService implements ContentService {
	
	@Value("${ecm_next.url}${content_service}${content_get_content}")
	private String searchContents_url;

	@Value("${ecm_next.url}${content_service}${content_create_content}")
	private String createContent_url;

	@Value("${ecm_next.url}${content_service}${content_update_content}")
	private String updateContent_url;

	@Value("${ecm_next.url}${content_service}${content_delete_content}")
	private String deleteContent_url;
	
	@Override
	public ResponseEntity<String> searchContents(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: tenantId cannot be empty") String tenantId,
			@NotEmpty(message = "Header: userId cannot be empty") String userId,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token, 
			String count,String property,String order,
			String offset,
			String sortOn, String sortOrder, String limit) {
		log.debug("Entering method: searchContents()");
		
		String url=searchContents_url;
		if(count 	!= null)
			url+="?count="+count;
		if(property != null)
			url+="?property="+property;
		if(order	!= null)
			url+="?order="+order;
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		if(offset 	!= null)
			headers.set("offset",offset);
		if(sortOn 	!= null)
			headers.set("sortOn",sortOn);
		if(sortOrder!= null)
			headers.set("sortOrder",sortOrder);
		if(limit 	!= null)
			headers.set("limit",limit);
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);
	}

	@Override
	public ResponseEntity<String> createContent(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: tenantId cannot be empty") String tenantId,
			@NotEmpty(message = "Header: userId cannot be empty") String userId,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Request: Body cannot be empty") String payLoad) {
		log.debug("Entering method: createContent()");
		log.info(createContent_url);
		
		log.info(org);
		log.info(tenantId);
		log.info(userId);
		log.info(payLoad);
		log.info(token);
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(createContent_url);		
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<>(payLoad,headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, request, String.class);
	}

	@Override
	public ResponseEntity<String> updateContent(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: tenantId cannot be empty") String tenantId,
			@NotEmpty(message = "Header: userId cannot be empty") String userId,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Request: Body cannot be empty") String payLoad,
			@NotEmpty(message = "Path: id cannot be empty") String id, String version) {
		log.debug("Entering method: updateContent()");
				
		String url=updateContent_url+"/"+id;
		if(version 	!= null)
			url+="?version="+version;
		log.info(url);
		
		log.info(org);
		log.info(tenantId);
		log.info(userId);
		log.info(payLoad);
		log.info(token);
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<>(payLoad,headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, request, String.class);
	}

	@Override
	public ResponseEntity<String> deleteContent(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: tenantId cannot be empty") String tenantId,
			@NotEmpty(message = "Header: userId cannot be empty") String userId,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Path: id cannot be empty") String id, String version) {
		log.debug("Entering method: updateContent()");
		
		String url=deleteContent_url+"/"+id;
		if(version 	!= null)
			url+="?version="+version;
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, request, String.class);
	}
}
