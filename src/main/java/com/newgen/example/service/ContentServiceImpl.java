/**
 * 
 */
package com.newgen.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.newgen.example.converter.ContentToContentDTOConverter;
import com.newgen.example.dto.ContentDTO;
import com.newgen.example.model.Content;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Nikhil Adlakha
 *
 */
@Service
@Slf4j
public class ContentServiceImpl extends GeneralService implements ContentService {
	
	@Autowired
	private ContentToContentDTOConverter converter;
	
	@Value("${ecm_next.url}+${content_service}+${content_get_content}")
	private String searchContents_url;

	@Value("${ecm_next.url}+${content_service}+${content_create_content}")
	private String createContent_url;

	@Value("${ecm_next.url}+${content_service}+${content_update_content}")
	private String updateContent_url;

	@Value("${ecm_next.url}+${content_service}+${content_delete_content}")
	private String deleteContent_url;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContentDTO> searchContents(@NotEmpty(message = "Header: org cannot be empty") String org,
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
		
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> response=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, List.class);
		
		List<ContentDTO> contentDTOs=new ArrayList<>();
		response.getBody().forEach((e) -> {contentDTOs.add(converter.convert((Content)e));});
		
		if(response.getStatusCode().is4xxClientError())
				return null;
		else	return contentDTOs;
	}

	@Override
	public String createContent(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: tenantId cannot be empty") String tenantId,
			@NotEmpty(message = "Header: userId cannot be empty") String userId,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Request: Body cannot be empty") String payLoad) {
		log.debug("Entering method: createContent()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(createContent_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		
		HttpEntity<String> request = new HttpEntity<>(payLoad,headers);
		
		ResponseEntity<String> response=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);
		
		if(response.getStatusCode().is4xxClientError())
				return null;
		else	return response.getBody();
	}

	@Override
	public String updateContent(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: tenantId cannot be empty") String tenantId,
			@NotEmpty(message = "Header: userId cannot be empty") String userId,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Request: Body cannot be empty") String payLoad,
			@NotEmpty(message = "Path: id cannot be empty") String id, String version) {
		log.debug("Entering method: updateContent()");
				
		String url=updateContent_url;
		if(version 	!= null)
			url+="?version="+version;
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		
		HttpEntity<String> request = new HttpEntity<>(payLoad,headers);
		
		ResponseEntity<String> response=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);
		
		if(response.getStatusCode().is4xxClientError())
				return null;
		else	return response.getBody();
	}

	@Override
	public String deleteContent(@NotEmpty(message = "Header: org cannot be empty") String org,
			@NotEmpty(message = "Header: tenantId cannot be empty") String tenantId,
			@NotEmpty(message = "Header: userId cannot be empty") String userId,
			@NotEmpty(message = "Header: accessToken cannot be empty") String token,
			@NotEmpty(message = "Path: id cannot be empty") String id, String version) {
		log.debug("Entering method: updateContent()");
		
		String url=deleteContent_url;
		if(version 	!= null)
			url+="?version="+version;
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		ResponseEntity<String> response=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);
		
		if(response.getStatusCode().is4xxClientError())
				return null;
		else	return response.getBody();
	}
}
