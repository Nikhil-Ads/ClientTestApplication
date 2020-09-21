package com.newgen.example.model;

import java.util.List;

public class InOutParameters {

	private Double requestPayloadSize;
	private Double responsePayloadSize;
	Folder folder;
	List<Folder> folders;

	public Double getRequestPayloadSize() {
		return requestPayloadSize;
	}

	public void setRequestPayloadSize(Double requestPayloadSize) {
		this.requestPayloadSize = requestPayloadSize;
	}

	public Double getResponsePayloadSize() {
		return responsePayloadSize;
	}

	public void setResponsePayloadSize(Double responsePayloadSize) {
		this.responsePayloadSize = responsePayloadSize;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

}
