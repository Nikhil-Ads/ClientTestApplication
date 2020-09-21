package com.newgen.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.newgen.example.controller.ExceptionThrower;

@Service
public class FolderFetchService extends ExceptionThrower{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${folder.service.url}")
	private String contentUrl;
	
	public String fetchFolder(String parentFolderId,
			String tenantId, String userId, 
			String sortOrder, String sortOn, 
			String folderOffset, String contentOffset, 
			String limit) {
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(contentUrl + "/folders/children/"+parentFolderId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("tenantId", tenantId);
		headers.set("userId", userId);
		headers.set("sortOrder", sortOrder);
		headers.set("sortOn", sortOn);
		headers.set("folderOffset", folderOffset);
		headers.set("contentOffset", contentOffset);
		headers.set("limit", limit);		
		HttpEntity<String> request = new HttpEntity<>(headers); 
		
		ResponseEntity<String> response= restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);
		
		return response.getBody();		
	}

}
