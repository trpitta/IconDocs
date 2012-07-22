package com.icondocs.dm.handler;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.automation.client.Constants;
import org.nuxeo.ecm.automation.client.adapters.DocumentService;
import org.nuxeo.ecm.automation.client.model.Blob;
import org.nuxeo.ecm.automation.client.model.DocRef;
import org.nuxeo.ecm.automation.client.model.Document;
import org.nuxeo.ecm.automation.client.model.Documents;
import org.nuxeo.ecm.automation.client.model.FileBlob;
import org.nuxeo.ecm.automation.client.model.PropertyMap;

import com.icondocs.dm.web.model.Docum;

public class DocumentHandler extends BaseHandler{

	public DocumentHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocumentHandler(String userName, String password) {
		super(userName, password);
		// TODO Auto-generated constructor stub
	}

	/*
	 * Classname reference for the log statements.
	 */
	private static final String className = DocumentHandler.class.toString();     	

	/*
	 * logger instance.
	 */
    private static final Log log = LogFactory.getLog(DocumentHandler.class);

    private static final boolean isDebug = log.isDebugEnabled();

    private static final boolean isInfo = log.isInfoEnabled();

	/**
	 * 
	 * @return
	 */
    public Documents getDomains() {
    	String methodName = "getDomains()";
		System.out.println(className+"."+methodName);

	     Documents documents = null;
		try {

			String query = "SELECT * FROM Domain WHERE ecm:currentLifeCycleState != 'deleted'"
	                + " ORDER BY dc:title";

		    DocumentService rs = getSession().getAdapter(DocumentService.class);
		    documents = rs.query(query);
			
 			System.out.println("Got the getDomains from repository");

 			 if(!documents.isEmpty()){
 			     System.out.println("The no.of domains: "+documents.size());
 			 }
  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documents;
    }

    /**
     * 
     */
    public Documents getDocuments(){
    	String methodName = "getDocuments()";
		System.out.println(className+"."+methodName);
		
    	Documents documents = null;
		try {
			String query = "SELECT * FROM Document";

            DocumentService rs = getSession().getAdapter(DocumentService.class);
            documents = rs.query(query);

 			System.out.println("Got the documents from repository");

 			 if(!documents.isEmpty()){
 			     System.out.println("The no.of documents: "+documents.size());
 		    	 for(Document doc : documents ){
 		    		   System.out.println("The Document Id:"+doc.getId());
 		    		   System.out.println("The Document Title:"+doc.getTitle());
 		    		   System.out.println("The Document Type:"+doc.getType());
 		    		   System.out.println("The Document Path:"+doc.getPath());
 		    	       System.out.println("The document Properties: " + doc.getProperties().size());
 		    	 }
 			 }

 			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documents;
	}

    /**
     * 
     */
    public Documents getDocuments(String uiid){
    	String methodName = "getDocuments(String uiid)";
		System.out.println(className+"."+methodName);
		
    	Documents documents = null;
		try {
	        String query = "SELECT * FROM Document WHERE ecm:uuid = '" + uiid + "' ";

            DocumentService rs = getSession().getAdapter(DocumentService.class);
            documents = rs.query(query);

 			 if(!documents.isEmpty()){
 			     System.out.println("The no.of documents: "+documents.size());
 			 }
  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documents;
	}

    public Documents getWorkSpace(){
    	String methodName = "getWorkSpace()";
		System.out.println(className+"."+methodName);
		
    	Documents documents = null;
		try {
			String query = "SELECT * FROM Workspace";
            DocumentService rs = getSession().getAdapter(DocumentService.class);
            documents = rs.query(query);

            if(!documents.isEmpty()){
            	System.out.println("The no.of documents: "+documents.size());
 			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documents;
	}
    
    public Document getDocument(String uiid) {
    	String methodName = "getDocument()";
		System.out.println(className+"."+methodName);

		Document document = null;

		try {
		
	      // Get the file document where blob was attached
 		  document = (Document) getSession().newRequest("Document.Fetch").setHeader(
	                Constants.HEADER_NX_SCHEMAS, "*").set("value",uiid).execute();
	        
	       System.out.println("The Document Id:"+document.getId());
 		   System.out.println("The Document Title:"+document.getTitle());
 		   System.out.println("The Document Type:"+document.getType());
 		   System.out.println("The Document Path:"+document.getPath());
	        
	        // get the file content property
	        PropertyMap map = document.getProperties().getMap("file:content");
	        // get the data URL
	        String path = map.getString("data");
	        System.out.println("The path value from: file:content"+path);
	 
	        // download the file from its remote location
	        Blob blob = (FileBlob) getSession().getFile(path);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return document;
    }

    public Document getDocument(Docum docum) {
    	String methodName = "getDocument()";
		System.out.println(className+"."+methodName);

		Document document = null;

		try {
		
	        // Get the file document where blob was attached
	        document = (Document) getSession().newRequest("Document.Fetch").setHeader(
	                Constants.HEADER_NX_SCHEMAS, "*").set("value",docum.getUiid()).execute();

	        // get the file content property
	        PropertyMap map = document.getProperties().getMap("file:content");
	        // get the data URL
	        String path = map.getString("data");
	        System.out.println("The path value from: file:content"+path);
	 
	        // download the file from its remote location
	        Blob blob = (FileBlob) getSession().getFile(path);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return document;
    }

    public Blob getBlob(String uiid) {

        Blob blob = null;
        String xpath = "file:content";
        try {
            blob = (Blob) getSession().newRequest("Blob.Get").setInput(
                    new DocRef(uiid)).set("xpath", xpath).execute();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
        return blob;
    }

    public Blob getPDF(String uiid) {

        Blob blob = null;
        try {
            blob = (Blob) getSession().newRequest("Blob.ToPDF").setInput(
                    new DocRef(uiid)).execute();

        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
        return blob;
    }
    
    public Blob getPictureView(String uiid, String viewName)  {

        Blob blob = null;
        viewName = "Medium";
        try {
            blob = (Blob) getSession().newRequest("Picture.getView").setInput(
                    new DocRef(uiid)).set("viewName", viewName).execute();


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return blob;
    }
    
    public Document addDocument(Docum docum) {
    	String methodName = "addDocument()";
		System.out.println(className+"."+methodName);
    
		Document document = null;

		try {

			Document root = (Document) getSession().newRequest("Document.Fetch").set(
	                "value", docum.getPath()).execute();

			// Create a File Document
			Document docCreate = (Document) getSession().newRequest("Document.Create").setInput(root).set("type", "File").set(
	                "name", docum.getName()).set("properties", "dc:title="+docum.getTitle()).execute();
	 
	        // Upload The file
	        File file = docum.getFile();
	        FileBlob fb = new FileBlob(file);
	        fb.setMimeType("text/xml");

	        // uploading a file will return null since we used HEADER_NX_VOIDOP
	        getSession().newRequest("Blob.Attach").setHeader(
	                Constants.HEADER_NX_VOIDOP, "true").setInput(fb)
	                .set("document", docum.getPath()+docum.getName()).execute();
	 
	        // Get the file document where blob was attached
	        document = (Document) getSession().newRequest(
	                "Document.Fetch").setHeader(
	                Constants.HEADER_NX_SCHEMAS, "*").set("value", docum.getPath()+docum.getName()).execute();
	        // get the file content property
	        PropertyMap map = document.getProperties().getMap("file:content");
	        // get the data URL
	        String path = map.getString("data");

	        // download the file from its remote location
	        FileBlob blob = (FileBlob) getSession().getFile(path);
	        // ... do something with the file
	        // at the end delete the temporary file
	        /*
	        blob.getFile().delete();
	        */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
		
		return document;
    }

    public void deleteDocument(Docum docum) {
    	String methodName = "deleteDocument(Docum docum)";
		System.out.println(className+"."+methodName);
    
		Document document = null;

		try {

			System.out.println("There is delete.get");
			document = (Document) getSession().newRequest("Document.Fetch").set(
	                "value", docum.getUiid()).execute();

			System.out.println("There is delete.delete");
			// Delete a Document
			getSession().newRequest("Document.Delete").setInput(document).execute();
			System.out.println("There is delete.deleted");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
		
    }
    
    public Document deleteDocumentAttachment(Docum docum) {
    	String methodName = "deleteDocumentAttachment(Docum docum)";
		System.out.println(className+"."+methodName);
    
		Document document = null;

		try {

	        // Get the file document where blob was attached
	        document = (Document) getSession().newRequest("Document.Fetch").setHeader(
		                Constants.HEADER_NX_SCHEMAS, "*").set("value",docum.getUiid()).execute();

	        // get the file content property
	        PropertyMap map = document.getProperties().getMap("file:content");
	        // get the data URL
	        String path = map.getString("data");

	        // download the file from its remote location
	        FileBlob blob = (FileBlob) getSession().getFile(path);

	        // delete the file
	        blob.getFile().delete();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
		
		return document;
    }
    

}
