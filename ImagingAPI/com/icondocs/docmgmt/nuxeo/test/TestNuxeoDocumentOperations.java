package com.icondocs.docmgmt.nuxeo.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import com.icondocs.docmgmt.api.DocumentConstants;
import com.icondocs.docmgmt.api.DocumentOperations;
import com.icondocs.docmgmt.exceptions.DocumentException;
import com.icondocs.docmgmt.nuxeo.impl.NuxeoDocumentOperations;

public class TestNuxeoDocumentOperations {

	String url = "http://localhost:8080/nuxeo/site/automation";
	String uName = "Administrator";
	String pwd = "Administrator";
	String wks = "ICONDOCS";
	
	@Test
	public void testAddDocSuccess() throws DocumentException {
		DocumentOperations ops = NuxeoDocumentOperations.getInstance(url, uName, pwd, wks);
		Map<String, Object> doc = new HashMap<String, Object>();
		doc.put("title", "test3");
		doc.put("title", "test3");
		doc.put(DocumentConstants.FILE_NAME, "file1.txt");
		doc.put(DocumentConstants.FILE_TYPE, "text/xml");
		doc.put(DocumentConstants.FILE_CONTENT, "C:/Documents and Settings/Administrator/Desktop/error.txt");
		assertNotNull("Document Id: ",ops.addDocument(doc,"File"));
	}
	
	@Test(expected = DocumentException.class)
	public void testAddDocFailure1() throws DocumentException {
		DocumentOperations ops = NuxeoDocumentOperations.getInstance(url, uName, pwd, wks);
		Map<String, Object> doc = new HashMap<String, Object>();
		doc.put("title", "test3");
		doc.put(DocumentConstants.FILE_NAME, null);
		doc.put(DocumentConstants.FILE_TYPE, "text/xml");
		doc.put(DocumentConstants.FILE_CONTENT, "C:/Documents and Settings/Administrator/Desktop/error.txt");
		ops.addDocument(doc,"File");
	}
	
	@Test(expected = DocumentException.class)
	public void testAddDocFailure2() throws DocumentException {
		DocumentOperations ops = NuxeoDocumentOperations.getInstance(url, uName, pwd, wks);
		Map<String, Object> doc = new HashMap<String, Object>();
		doc.put("title", "test3");
		doc.put(DocumentConstants.FILE_NAME, "file1.txt");
		doc.put(DocumentConstants.FILE_TYPE, null);
		doc.put(DocumentConstants.FILE_CONTENT, "C:/Documents and Settings/Administrator/Desktop/error.txt");
		ops.addDocument(doc,"File");
	}
	
	@Test(expected = DocumentException.class)
	public void testAddDocFailure3() throws DocumentException {
		DocumentOperations ops = NuxeoDocumentOperations.getInstance(url, uName, pwd, wks);
		Map<String, Object> doc = new HashMap<String, Object>();
		doc.put("title", "test3");
		doc.put(DocumentConstants.FILE_NAME, "file1.txt");
		doc.put(DocumentConstants.FILE_TYPE, "text/xml");
		doc.put(DocumentConstants.FILE_CONTENT, null);
		ops.addDocument(doc,"File");
	}
	
	@Test(expected = DocumentException.class)
	public void testAddDocFailure4() throws DocumentException {
		DocumentOperations ops = NuxeoDocumentOperations.getInstance(url, uName, pwd, wks);
		ops.addDocument(null,"File");
	}
	
	@Test
	public void testGetDocSuccess() throws DocumentException {
		DocumentOperations ops = NuxeoDocumentOperations.getInstance(url, uName, pwd, wks);
		Map<String, Object> docContent = ops.getContent("f1f4f0ee-c908-4049-b56e-cb8deca1e9c2");
		assertNotNull("Document Content: ",docContent);
	}
	
	@Test(expected = DocumentException.class)
	public void testGetDocFailure() throws DocumentException {
		DocumentOperations ops = NuxeoDocumentOperations.getInstance(url, uName, pwd, wks);
		ops.getContent(null);
	}
	
	@Test(expected = DocumentException.class)
	public void testGetDocFailure1() throws DocumentException {
		DocumentOperations ops = NuxeoDocumentOperations.getInstance(url, uName, pwd, wks);
		ops.getContent("Hello_World!!");
	}
	
}
