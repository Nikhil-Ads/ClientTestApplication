package com.newgen.example.service;

import org.springframework.http.ResponseEntity;

import com.newgen.example.dto.FolderDTO;
import com.newgen.example.exception.CustomException;

public interface FolderService {
	
	public ResponseEntity<FolderDTO> createFolder(String org, String tenantId, String userId, String token, FolderDTO folderDTO) throws CustomException;
	
	public ResponseEntity<String> searchFolders(String org, String tenantId, String token, String query);
	
	public ResponseEntity<String> deleteFolder(String org, String tenantId, String userId, String token, String id, String version, Boolean recursive, Boolean isCabinet);

	public ResponseEntity<FolderDTO> updateFolder(String id,String org, String tenantId, String userId, String token, FolderDTO folderDTO, String version) throws CustomException;
}
