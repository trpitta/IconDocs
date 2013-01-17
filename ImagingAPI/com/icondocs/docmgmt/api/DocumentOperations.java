package com.icondocs.docmgmt.api;

import java.util.Map;

import com.icondocs.docmgmt.exceptions.DocumentException;

public interface DocumentOperations {

	public Map<String,Object> getContent(String docid) throws DocumentException;
	public String addDocument(Map<String,Object> document, String docClass) throws DocumentException;
	
}
