/**
 * 
 */
package com.newgen.example.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.newgen.example.dto.FolderDTO;
import com.newgen.example.model.Folder;

/**
 * @author nikhil.adlakha
 *
 */
@Component
public class FolderToFolderDTOConverter implements Converter<Folder, FolderDTO> {

	@Override
	public FolderDTO convert(Folder folder) {
		if(folder == null)
				return null;
		else {	final FolderDTO folderDTO=new FolderDTO();
				folderDTO.setFolderName(folder.getFolderName());
				folderDTO.setFolderType(folder.getFolderType());
				folderDTO.setMetadata(folder.getMetadata());
				folderDTO.setOwnerId(folder.getOwnerId());
				folderDTO.setOwnerName(folder.getOwnerName());
				folderDTO.setParentFolderId(folder.getParentFolderId());
				folderDTO.setUsedFor(folder.getUsedFor());
				folderDTO.setComments(folder.getComments());		
				return folderDTO;					
		}
	}

}
