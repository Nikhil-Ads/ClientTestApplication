/**
 * 
 */
package com.newgen.example.converter;

import org.springframework.core.convert.converter.Converter;

import com.newgen.example.dto.ContentDTO;
import com.newgen.example.model.Content;

/**
 * @author Nikhil Adlakha
 *
 */
public class ContentToContentDTOConverter implements Converter<Content, ContentDTO> {

	@Override
	public ContentDTO convert(Content source) {
		if(source == null)
				return null;
		else {  final ContentDTO dest=new ContentDTO();
				
				dest.setAccessCount(source.getAccessCount());
				dest.setAccessDateTime(source.getAccessDateTime());
				dest.setChangeSetContentId(source.getChangeSetContentId());
				dest.setCheckedOut(source.getCheckedOut());
				dest.setCheckedOutBy(source.getCheckedOutBy());
				dest.setCheckedOutTime(source.getCheckedOutTime());
				dest.setComments(source.getComments());
				dest.setContent(source.getContent());
				dest.setContentLocationId(source.getContentLocationId());
				dest.setContentType(source.getContentType());
				dest.setCreationDateTime(source.getCreationDateTime());
				dest.setDataclass(source.getDataclass());
				dest.setDataClassText(source.getDataClassText());
				dest.setDocumentSize(source.getDocumentSize());
				dest.setDocumentType(source.getDocumentType());
				dest.setFlag(source.getFlag());
				dest.setId(source.getId());
				dest.setIndexed(source.getIndexed());
				dest.setInTrash(source.getInTrash());
				dest.setLastCheckedInBy(source.getLastCheckedInBy());
				dest.setLastCheckedInbyUserName(source.getLastCheckedInbyUserName());
				dest.setLatest(source.getLatest());
				dest.setMetadata(source.getMetadata());
				dest.setName(source.getName());
				dest.setNoOfPages(source.getNoOfPages());
				dest.setOwnerId(source.getOwnerId());
				dest.setOwnerName(source.getOwnerName());
				dest.setParentFolderId(source.getParentFolderId());
				dest.setParentFolderName(source.getParentFolderName());
				dest.setPreviousVersion(source.getPreviousVersion());
				dest.setPreviousVersionContentId(source.getPreviousVersionContentId());
				dest.setPrimaryContentId(source.getPrimaryContentId());
				dest.setPrivilege(source.getPrivilege());
				dest.setRevisedDateTime(source.getRevisedDateTime());
				dest.setUploadIn(source.getUploadIn());
				dest.setVersion(source.getVersion());
				dest.setVersionComments(source.getVersionComments());
				return dest;				
			}
	}
	
	

}
