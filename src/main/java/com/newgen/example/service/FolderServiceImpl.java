/**
 * 
 */
package com.newgen.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.newgen.example.converter.FolderToFolderDTOConverter;
import com.newgen.example.dto.FolderDTO;
import com.newgen.example.exception.CustomException;
import com.newgen.example.model.Folder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Nikhil Adlakha
 *
 */
@Slf4j
public class FolderServiceImpl extends GeneralService implements FolderService {
	
	@Autowired
	private FolderToFolderDTOConverter converter;
	
	@Value("${ecm_next.url}+${folder_service}+${folder_create_folder}")
	private String createFolder_url;
	
	@Value("${ecm_next.url}+${folder_service}+${user_search_folder}")
	private String searchFolders_url;
	
	@Value("${ecm_next.url}+${folder_service}+${folder_delete_folder}")
	private String deleteFolder_url;

	@Override
	public FolderDTO createFolder(String org, String tenantId, String userId, String token, FolderDTO folderDTO) throws CustomException {
		log.debug("Entering method: createFolder()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(createFolder_url);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org",org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		
		HttpEntity<FolderDTO> request=new HttpEntity<>(folderDTO,headers);
		
		ResponseEntity<Folder> response=restTemplate.exchange(builder.toUriString(), HttpMethod.POST , request, Folder.class);
		
		HttpStatus status=response.getStatusCode();
		if(status.is4xxClientError()) { 
				throwCustomException(response.getBody().toString(), status);
				return null;}
		else if(status.is5xxServerError())
				return null;
		else 	return converter.convert(response.getBody());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<FolderDTO> searchFolders(String org, String tenantId, String userId, String token, String query) {
		log.debug("Entering method: getUser()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(searchFolders_url+"/"+query);
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", org);
		headers.set("tenantId",tenantId);
		headers.set("userId",userId);
		headers.set("accessToken",token);
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		ResponseEntity<List> response=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, List.class);
		
		List<FolderDTO> folderDTOs=new ArrayList<>();
		response.getBody().forEach((e) -> {folderDTOs.add(converter.convert((Folder)e));});
		
		if(response.getStatusCode().is4xxClientError())
				return null;
		else	return folderDTOs;
	}

	@Override
	public String deleteFolder(String org, String tenantId, String userId, String token, String id) {
		log.debug("Entering method: getUser()");
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(deleteFolder_url+"/"+id);
		
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
