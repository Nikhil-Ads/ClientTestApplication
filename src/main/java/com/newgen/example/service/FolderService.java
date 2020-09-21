package com.newgen.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.newgen.example.dto.FolderDTO;
import com.newgen.example.exception.CustomException;

@Service
public interface FolderService {
	
	public FolderDTO createFolder(String org, String tenantId, String userId, String token, FolderDTO folderDTO) throws CustomException;
	
	public List<FolderDTO> searchFolders(String org, String tenantId, String userId, String token, String query);
	
	public String deleteFolder(String org, String tenantId, String userId, String token, String id);
}
