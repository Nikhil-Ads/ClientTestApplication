package com.newgen.example.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.ToString;

@Entity
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Content {
	
	@Id
	@Pattern(regexp = "^[0-9A-Za-z]+$", message = "Content Id is not Valid")
	private String id;
	
	@NotNull
	@Pattern(regexp = "^[0-9A-Za-z \\-._@#$%^&!]+$", message = "Parent Folder Name is not Valid")	
	private String name;
	
	private String documentType;
	
	@Pattern(regexp = "^[0-9,]{1,} [A-Z]?B", message = "Document Size is not Valid. Document Size must be in the format: ... (K|M|G|T)B")
	private String documentSize;
	
	private Object content;
	
	private String contentLocationId;
	
	private String contentType;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date creationDateTime;
	
	@NotNull
	@Pattern(regexp = "^[0-9A-Za-z]{24}$", message = "Tenant Id must be of 24 Characters")
	private String tenantId;
	
	@NotNull
	@Pattern(regexp = "^[0-9A-Za-z.-_]{5,}$", message = "Token must be more than 5 characters long.")
	private String token;
	
	private String uploadIn;
	
	@Version
	private Long version;
		
	private String versionComments;
	
	private String primaryContentId;
	
	@Enumerated(value = EnumType.STRING)
	private Privilege privilege;	
	
	@Enumerated(value = EnumType.STRING)
	private Flag flag;	
	
	/**Comments for the Document.*/
	private String comments;
	
	private String noOfPages;
	
	/*Content MetaData Properties*/
	
	@NotNull
	@Pattern(regexp = "^[0-9A-Za-z]+$", message = "Parent Folder Id is not Valid")
	private String parentFolderId;
	
	@NotNull
	@Pattern(regexp = "^[0-9A-Za-z]+$", message = "Parent Folder Name is not Valid")
	private String parentFolderName;
	
	private JSONObject parentHierarchy;
	
	private Map<String, String> metadata;
	
	private Map<String, String> dataclass;
	
	private String dataClassText;
	
	private String changeSetContentId;	
	
	private Boolean checkedOut;
	
	private String checkedOutBy;
	
	private String checkedOutTime;
	
	private String lastCheckedInBy;
	
	private String lastCheckedInbyUserName;
	
	private Boolean latest;
	
	private Boolean indexed;
	
	private Boolean inTrash;

	private String previousVersionContentId;
	
	private Long   previousVersion;					
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date	accessDateTime;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date	revisedDateTime;
	
	private Integer accessCount;
	
	private String odId;
	
	@Pattern(regexp = "^[0-9A-Za-z]{24}$", message = "Owner Id must be of 24 Characters")	
	private String ownerId;
		
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Owner name must contain only alphabets or spaces.")	
	private String ownerName;

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the documentType
	 */
	public final String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType the documentType to set
	 */
	public final void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	/**
	 * @return the documentSize
	 */
	public final String getDocumentSize() {
		return documentSize;
	}

	/**
	 * @param documentSize the documentSize to set
	 */
	public final void setDocumentSize(String documentSize) {
		this.documentSize = documentSize;
	}

	/**
	 * @return the content
	 */
	public final Object getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public final void setContent(Object content) {
		this.content = content;
	}

	/**
	 * @return the contentLocationId
	 */
	public final String getContentLocationId() {
		return contentLocationId;
	}

	/**
	 * @param contentLocationId the contentLocationId to set
	 */
	public final void setContentLocationId(String contentLocationId) {
		this.contentLocationId = contentLocationId;
	}

	/**
	 * @return the contentType
	 */
	public final String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public final void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the creationDateTime
	 */
	public final Date getCreationDateTime() {
		return creationDateTime;
	}

	/**
	 * @param creationDateTime the creationDateTime to set
	 */
	public final void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	/**
	 * @return the tenantId
	 */
	public final String getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public final void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the token
	 */
	public final String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public final void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the uploadIn
	 */
	public final String getUploadIn() {
		return uploadIn;
	}

	/**
	 * @param uploadIn the uploadIn to set
	 */
	public final void setUploadIn(String uploadIn) {
		this.uploadIn = uploadIn;
	}

	/**
	 * @return the version
	 */
	public final Long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public final void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * @return the versionComments
	 */
	public final String getVersionComments() {
		return versionComments;
	}

	/**
	 * @param versionComments the versionComments to set
	 */
	public final void setVersionComments(String versionComments) {
		this.versionComments = versionComments;
	}

	/**
	 * @return the primaryContentId
	 */
	public final String getPrimaryContentId() {
		return primaryContentId;
	}

	/**
	 * @param primaryContentId the primaryContentId to set
	 */
	public final void setPrimaryContentId(String primaryContentId) {
		this.primaryContentId = primaryContentId;
	}

	/**
	 * @return the privilege
	 */
	public final Privilege getPrivilege() {
		return privilege;
	}

	/**
	 * @param privilege the privilege to set
	 */
	public final void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	/**
	 * @return the flag
	 */
	public final Flag getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public final void setFlag(Flag flag) {
		this.flag = flag;
	}

	/**
	 * @return the comments
	 */
	public final String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public final void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the noOfPages
	 */
	public final String getNoOfPages() {
		return noOfPages;
	}

	/**
	 * @param noOfPages the noOfPages to set
	 */
	public final void setNoOfPages(String noOfPages) {
		this.noOfPages = noOfPages;
	}

	/**
	 * @return the parentFolderId
	 */
	public final String getParentFolderId() {
		return parentFolderId;
	}

	/**
	 * @param parentFolderId the parentFolderId to set
	 */
	public final void setParentFolderId(String parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	/**
	 * @return the parentFolderName
	 */
	public final String getParentFolderName() {
		return parentFolderName;
	}

	/**
	 * @param parentFolderName the parentFolderName to set
	 */
	public final void setParentFolderName(String parentFolderName) {
		this.parentFolderName = parentFolderName;
	}

	/**
	 * @return the parentHierarchy
	 */
	public final JSONObject getParentHierarchy() {
		return parentHierarchy;
	}

	/**
	 * @param parentHierarchy the parentHierarchy to set
	 */
	public final void setParentHierarchy(JSONObject parentHierarchy) {
		this.parentHierarchy = parentHierarchy;
	}

	/**
	 * @return the metadata
	 */
	public final Map<String, String> getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public final void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	/**
	 * @return the dataclass
	 */
	public final Map<String, String> getDataclass() {
		return dataclass;
	}

	/**
	 * @param dataclass the dataclass to set
	 */
	public final void setDataclass(Map<String, String> dataclass) {
		this.dataclass = dataclass;
	}

	/**
	 * @return the dataClassText
	 */
	public final String getDataClassText() {
		return dataClassText;
	}

	/**
	 * @param dataClassText the dataClassText to set
	 */
	public final void setDataClassText(String dataClassText) {
		this.dataClassText = dataClassText;
	}

	/**
	 * @return the changeSetContentId
	 */
	public final String getChangeSetContentId() {
		return changeSetContentId;
	}

	/**
	 * @param changeSetContentId the changeSetContentId to set
	 */
	public final void setChangeSetContentId(String changeSetContentId) {
		this.changeSetContentId = changeSetContentId;
	}

	/**
	 * @return the checkedOut
	 */
	public final Boolean getCheckedOut() {
		return checkedOut;
	}

	/**
	 * @param checkedOut the checkedOut to set
	 */
	public final void setCheckedOut(Boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	/**
	 * @return the checkedOutBy
	 */
	public final String getCheckedOutBy() {
		return checkedOutBy;
	}

	/**
	 * @param checkedOutBy the checkedOutBy to set
	 */
	public final void setCheckedOutBy(String checkedOutBy) {
		this.checkedOutBy = checkedOutBy;
	}

	/**
	 * @return the checkedOutTime
	 */
	public final String getCheckedOutTime() {
		return checkedOutTime;
	}

	/**
	 * @param checkedOutTime the checkedOutTime to set
	 */
	public final void setCheckedOutTime(String checkedOutTime) {
		this.checkedOutTime = checkedOutTime;
	}

	/**
	 * @return the lastCheckedInBy
	 */
	public final String getLastCheckedInBy() {
		return lastCheckedInBy;
	}

	/**
	 * @param lastCheckedInBy the lastCheckedInBy to set
	 */
	public final void setLastCheckedInBy(String lastCheckedInBy) {
		this.lastCheckedInBy = lastCheckedInBy;
	}

	/**
	 * @return the lastCheckedInbyUserName
	 */
	public final String getLastCheckedInbyUserName() {
		return lastCheckedInbyUserName;
	}

	/**
	 * @param lastCheckedInbyUserName the lastCheckedInbyUserName to set
	 */
	public final void setLastCheckedInbyUserName(String lastCheckedInbyUserName) {
		this.lastCheckedInbyUserName = lastCheckedInbyUserName;
	}

	/**
	 * @return the latest
	 */
	public final Boolean getLatest() {
		return latest;
	}

	/**
	 * @param latest the latest to set
	 */
	public final void setLatest(Boolean latest) {
		this.latest = latest;
	}

	/**
	 * @return the indexed
	 */
	public final Boolean getIndexed() {
		return indexed;
	}

	/**
	 * @param indexed the indexed to set
	 */
	public final void setIndexed(Boolean indexed) {
		this.indexed = indexed;
	}

	/**
	 * @return the inTrash
	 */
	public final Boolean getInTrash() {
		return inTrash;
	}

	/**
	 * @param inTrash the inTrash to set
	 */
	public final void setInTrash(Boolean inTrash) {
		this.inTrash = inTrash;
	}

	/**
	 * @return the previousVersionContentId
	 */
	public final String getPreviousVersionContentId() {
		return previousVersionContentId;
	}

	/**
	 * @param previousVersionContentId the previousVersionContentId to set
	 */
	public final void setPreviousVersionContentId(String previousVersionContentId) {
		this.previousVersionContentId = previousVersionContentId;
	}

	/**
	 * @return the previousVersion
	 */
	public final Long getPreviousVersion() {
		return previousVersion;
	}

	/**
	 * @param previousVersion the previousVersion to set
	 */
	public final void setPreviousVersion(Long previousVersion) {
		this.previousVersion = previousVersion;
	}

	/**
	 * @return the accessDateTime
	 */
	public final Date getAccessDateTime() {
		return accessDateTime;
	}

	/**
	 * @param accessDateTime the accessDateTime to set
	 */
	public final void setAccessDateTime(Date accessDateTime) {
		this.accessDateTime = accessDateTime;
	}

	/**
	 * @return the revisedDateTime
	 */
	public final Date getRevisedDateTime() {
		return revisedDateTime;
	}

	/**
	 * @param revisedDateTime the revisedDateTime to set
	 */
	public final void setRevisedDateTime(Date revisedDateTime) {
		this.revisedDateTime = revisedDateTime;
	}

	/**
	 * @return the accessCount
	 */
	public final Integer getAccessCount() {
		return accessCount;
	}

	/**
	 * @param accessCount the accessCount to set
	 */
	public final void setAccessCount(Integer accessCount) {
		this.accessCount = accessCount;
	}

	/**
	 * @return the odId
	 */
	public final String getOdId() {
		return odId;
	}

	/**
	 * @param odId the odId to set
	 */
	public final void setOdId(String odId) {
		this.odId = odId;
	}

	/**
	 * @return the ownerId
	 */
	public final String getOwnerId() {
		return ownerId;
	}

	/**
	 * @param ownerId the ownerId to set
	 */
	public final void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @return the ownerName
	 */
	public final String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName the ownerName to set
	 */
	public final void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
