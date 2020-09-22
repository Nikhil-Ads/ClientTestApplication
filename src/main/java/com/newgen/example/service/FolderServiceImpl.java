/**
 * 
 */
package com.newgen.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.newgen.example.dto.FolderDTO;
import com.newgen.example.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Nikhil Adlakha
 *
 */
@Service
@Slf4j
public class FolderServiceImpl extends GeneralService implements FolderService {
	
	@Value("${ecm_next.url}${folder_service}${folder_create_folder}")
	private String createFolder_url;
	
	@Value("${ecm_next.url}${folder_service}${folder_search_folder}")
	private String searchFolders_url;
	
	@Value("${ecm_next.url}${folder_service}${folder_delete_folder}")
	private String deleteFolder_url;
	
	@Value("${ecm_next.url}${folder_service}${folder_update_folder}")
	private String updateFolder_url;

	@Override
	public ResponseEntity<FolderDTO> createFolder(String org, String tenantId, String userId, String token, FolderDTO folderDTO) throws CustomException {
		log.debug("Entering method: createFolder()");
		
		log.info(createFolder_url);
		log.info(folderDTO.toString());
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(createFolder_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org",org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<FolderDTO> request=new HttpEntity<>(folderDTO,headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.POST , request, FolderDTO.class);
	}

	@Override
	public ResponseEntity<String> searchFolders(String org, String tenantId, String token, String query) {
		log.debug("Entering method: getUser()");
		
		UriComponentsBuilder builder=null;
		if(query != null)
				builder=UriComponentsBuilder.fromUriString(searchFolders_url+query);
		else	builder=UriComponentsBuilder.fromUriString(searchFolders_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("tenantId",tenantId);
		headers.set("accessToken",token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);
	}

	@Override
	public ResponseEntity<String> deleteFolder(String org, String tenantId, String userId, String token, String id,
											   String version, Boolean recursive, Boolean isCabinet) {
		log.debug("Entering method: getUser()");
		
		String url=deleteFolder_url+"/"+id;
		
		if(version != null)
			url+="?version="+version;
		if(recursive != null)
			url+="?recursive="+recursive;
		if(isCabinet != null)
			url+="?isCabinet="+isCabinet;		
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, String.class);
	}

	@Override
	public ResponseEntity<FolderDTO> updateFolder(String id, String org, String tenantId, String userId, String token,
			FolderDTO folderDTO, String version) throws CustomException {
		log.debug("Entering method: updateFolder()");
		
		String url=updateFolder_url+"/"+id;
		if(version != null)
			url+="?version="+version;		
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org",org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<FolderDTO> request=new HttpEntity<>(folderDTO,headers);
		
		return restTemplate.exchange(builder.toUriString(), HttpMethod.PUT , request, FolderDTO.class);
	}

}
