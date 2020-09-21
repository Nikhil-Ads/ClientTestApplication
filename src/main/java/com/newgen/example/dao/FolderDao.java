package com.newgen.example.dao;

import java.util.List;
import java.util.Map;

import com.newgen.example.model.Folder;
import com.newgen.example.model.InOutParameters;

public interface FolderDao {
	public Folder findAndRemoveById(String id, String tenantId);

	public Folder findAndRemoveByIdAndVersion(String id, String version, String tenantId);

	public List<Folder> findByParentFolderId(String id, String tenantId);

	public Folder findAndModify(String id, String updateFolderParams, Long version, String tenantId);

	public InOutParameters findAndModify(String id, Map<String, String> updateFolderParams, Long version, String tenantId);

	public InOutParameters findAllFolders(Map<String, String[]> paramMap, String tenantId);

	public InOutParameters findAllFoldersByPage(Map<String, String[]> paramMap, String tenantId, int pno);

	public List<Folder> findByFolderName(String folderName, String tenantId);

	public InOutParameters insert(Folder folder);

	public InOutParameters findById(String id, String tenantId);

	public Folder findCabinetById(String id, String tenantId);

	public Folder updateParentFolderId(String id, String targetId, Long version, String tenantId);

	public Folder updateChildrenCount(String id, int count, String tenantId);

	public List<Folder> findAllContentsByMetadata(String searchParams, String tenantId);

	public List<Folder> findByParentFolderIdInGroup(List<String> userIds);

	public List<Folder> findByPrivateFolderId(List<String> folderIds);

	public Folder getFolderByRevisedDateTime(String id, String tenantId);

}
