package com.icondocs.dm.web.model;

import java.io.File;

public class Docum {
	
	private String uiid;

	private String name;
	
	private String title;
	
	private String location;

	private String path = "/default-domain/workspaces/";
	
	private File file;

	private String type;
	
	private String mimeType = "image/jpeg";
	
	public String getUiid() {
		return uiid;
	}

	public void setUiid(String uiid) {
		this.uiid = uiid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		path = "/default-domain/workspaces/";
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public File getFile() {
		file = new File (this.location);
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
}
