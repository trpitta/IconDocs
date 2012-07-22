package com.icondocs.dm.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.automation.client.Session;
import org.nuxeo.ecm.automation.client.jaxrs.impl.HttpAutomationClient;

public class BaseHandler {

	private static final String nuxeoURL = "http://localhost:8080/nuxeo/site/automation";

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

    private HttpAutomationClient client;

    private Session session;

    private String userName = "Administrator";
    
    private String password = "Administrator";

	public BaseHandler() {
		super();
	}
    /**
     * 
     * @param userName
     * @param password
     */
	public BaseHandler(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}


    public HttpAutomationClient getClient() {
    	
    	if(client == null){
    		client = new HttpAutomationClient(nuxeoURL);
    	}
    	
        return client;
    }

    public Session getSession() {
    	
    	if(client == null){
    		getClient();
    	}
    	
        if (session == null) {
            session = client.getSession(userName, password);
        } 
        return session;
    }

	public void release() {
	    if (client != null) {
	        client.shutdown();
	        client = null;
	        session = null;
	    }
	}
	
}
