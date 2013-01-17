package com.icondocs.docmgmt.nuxeo.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.nuxeo.ecm.automation.client.Constants;
import org.nuxeo.ecm.automation.client.OperationRequest;
import org.nuxeo.ecm.automation.client.Session;
import org.nuxeo.ecm.automation.client.jaxrs.impl.HttpAutomationClient;
import org.nuxeo.ecm.automation.client.model.Blob;
import org.nuxeo.ecm.automation.client.model.DocRef;
import org.nuxeo.ecm.automation.client.model.Document;
import org.nuxeo.ecm.automation.client.model.FileBlob;
import org.nuxeo.ecm.automation.client.model.PathRef;
import org.nuxeo.ecm.automation.client.model.PropertyMap;

import com.icondocs.docmgmt.api.DocumentConstants;
import com.icondocs.docmgmt.api.DocumentOperations;
import com.icondocs.docmgmt.exceptions.DocumentException;

public class NuxeoDocumentOperations implements DocumentOperations {

	private String userName;
	private String password;
	private String URL;
	private String workspace;

	private NuxeoDocumentOperations(String url, String uName, String pwd, String wsp) {
		userName = uName;
		password = pwd;
		URL = url;
		workspace = NuxeoConsants.DEFAULT_WORKAPCE_PATH + wsp;
	}

	public static DocumentOperations getInstance(String url, String uName, String pwd, String wsp) {
		return new NuxeoDocumentOperations(url, uName, pwd, wsp);
	}

	@Override
	public Map<String, Object> getContent(String docid) throws DocumentException {

		HttpAutomationClient client = null;
		Session session = null;
		Map<String, Object> docContent = null;
		String xpath = "file:content";

		try {
			if((docid ==null) || (docid.trim().length() == 0)){
				throw new DocumentException(
						DocumentConstants.ERR_CODE_EMPTY_DOCID,
						DocumentConstants.ERR_MSG_EMPTY_DOCID);
			}
			client = new HttpAutomationClient(getURL());
			session = client.getSession(getUserName(), getPassword());
			OperationRequest request = session.newRequest("Blob.Get");
			request.setInput(new DocRef(docid)).set("xpath", xpath);
			Blob blob = (Blob) request.execute();
			byte[] content = null;

			if (blob != null) {
				content = IOUtils.toByteArray(blob.getStream());
				docContent = new HashMap<String, Object>();
				docContent.put(DocumentConstants.FILE_NAME, blob.getFileName());
				docContent.put(DocumentConstants.FILE_TYPE, blob.getMimeType());
				docContent.put(DocumentConstants.FILE_CONTENT, content);
			}
		} catch (Exception ex) {
			throw new DocumentException(ex, DocumentConstants.ERR_CODE_GENERIC,
					DocumentConstants.ERR_MSG_GENERIC);
		} finally {

			if (session != null) {
				session.close();
				session = null;
			}

			if (client != null) {
				client.shutdown();
				client = null;
			}
		}

		return docContent;
	}

	@Override
	public String addDocument(Map<String, Object> document, String docClass) throws DocumentException {

		HttpAutomationClient client = null;
		Session session = null;
		String docid = null;
		String uniqueFileName = null;
		
		try {
			
			String fileName = (String) document.get(DocumentConstants.FILE_NAME);
			String mimeType = (String) document.get(DocumentConstants.FILE_TYPE);
			Object fileContent = document.get(DocumentConstants.FILE_CONTENT);
			
			if((fileName == null) || (mimeType == null) || !(fileContent instanceof String)){
				throw new DocumentException(
						DocumentConstants.ERR_CODE_ADDDOC_MISSING_REQ_PARAM,
						DocumentConstants.ERR_MSG_ADDDOC_MISSING_REQ_PARAM);
			}
			
			document.remove(DocumentConstants.FILE_NAME);
			document.remove(DocumentConstants.FILE_TYPE);
			document.remove(DocumentConstants.FILE_CONTENT);
			uniqueFileName = getUUID(fileName);
			PropertyMap props = new PropertyMap();
			
			for(Map.Entry<String, Object> property : document.entrySet()){
				props.set("dc:"+property.getKey(),String.valueOf(property.getValue()));	
			}
			
			client = new HttpAutomationClient(getURL());
			session = client.getSession(getUserName(), getPassword());
						
			OperationRequest docReq = session.newRequest("Document.Create");
			docReq.setInput(new PathRef(getWorkspace()));
			docReq.set("type", docClass);
			docReq.set("name", uniqueFileName);
			docReq.set("properties", props);
			Document doc = (Document) docReq.execute();
			docid = doc.getId();
			// Upload The file
	        File file = new File((String)fileContent);
	        FileBlob fb = new FileBlob(file);
	        fb.setMimeType(mimeType);
	        fb.setFileName(fileName);
	        OperationRequest blobReq = session.newRequest("Blob.Attach");
	        // uploading a file will return null since we used HEADER_NX_VOIDOP
	        blobReq.setHeader(Constants.HEADER_NX_VOIDOP, "true");
	        blobReq.setInput(fb);
  	        blobReq.set("document", getWorkspace()+"/"+uniqueFileName);
  	        blobReq.execute();
			
		} catch (Exception ex) {
			throw new DocumentException(ex, DocumentConstants.ERR_CODE_GENERIC,
					DocumentConstants.ERR_MSG_GENERIC);
		} finally {
			
			if (session != null) {
				session.close();
				session = null;
			}

			if (client != null) {
				client.shutdown();
				client = null;
			}
		}

		return docid;
	}

	private String getUserName() {
		return userName;
	}

	private String getPassword() {
		return password;
	}

	private String getURL() {
		return URL;
	}

	private String getWorkspace() {
		return workspace;
	}

	private String getUUID(String fileName) {
		return UUID.randomUUID().toString()+"_"+fileName;
	}
}
