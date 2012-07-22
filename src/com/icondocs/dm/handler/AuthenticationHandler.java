package com.icondocs.dm.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.automation.client.Session;
import org.nuxeo.ecm.automation.client.jaxrs.impl.HttpAutomationClient;
import org.nuxeo.ecm.automation.client.jaxrs.spi.auth.PortalSSOAuthInterceptor;

public class AuthenticationHandler extends BaseHandler {

	public AuthenticationHandler(String userName, String password) {
		super(userName, password);
		// TODO Auto-generated constructor stub
	}

	/*
	 * Classname reference for the log statements.
	 */
	private static final String className = AuthenticationHandler.class.toString();     	

	/*
	 * logger instance.
	 */
    private static final Log log = LogFactory.getLog(AuthenticationHandler.class);

    private static final boolean isDebug = log.isDebugEnabled();

    private static final boolean isInfo = log.isInfoEnabled();

    /**
     * 
     * @return
     */
    public boolean authenticate(String userName, String password) {

    	String methodName = "authenticate()";
		System.out.println(className+"."+methodName);

	     boolean validUser = false;
		try {
 			System.out.println("Got here");
			getClient().setRequestInterceptor(new PortalSSOAuthInterceptor(password, userName));
			Session session = getClient().getSession();		

 			System.out.println("Got here");
 			release();

		} catch (Exception e) {
			// TODO Auto-generated catch block
	    	System.out.println("Hey, Auth exception");
			e.printStackTrace();
		}
		return validUser;
    
    }

}
