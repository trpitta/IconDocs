package com.icondocs.dm.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.automation.client.model.Blob;
import org.nuxeo.ecm.automation.client.model.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.icondocs.dm.handler.AuthenticationHandler;
import com.icondocs.dm.handler.DocumentHandler;
import com.icondocs.dm.web.model.Docum;
import com.icondocs.dm.web.model.User;

@Controller
@RequestMapping("/docmgt")
public class DocumentController {

	/*
	 * Classname reference for the log statements.
	 */
	private static final String className = DocumentController.class.toString();     	

	/*
	 * logger instance.
	 */
    private static final Log log = LogFactory.getLog(DocumentController.class);

    private static final boolean isDebug = log.isDebugEnabled();

    private static final boolean isInfo = log.isInfoEnabled();
    
    private DocumentHandler documentHandler;

    @RequestMapping("/domains")
    public ModelAndView getDomains() {
       ModelAndView mav = new ModelAndView();
       documentHandler = new DocumentHandler();
       
       mav.setViewName("domainList");
       mav.addObject("domainList", documentHandler.getDomains().iterator());
       
       return mav;
    }

    @RequestMapping("/documents")
    public ModelAndView getDocuments() {
       ModelAndView mav = new ModelAndView();

       documentHandler = new DocumentHandler();
       
       mav.setViewName("documentList");
       mav.addObject("documentList", documentHandler.getDocuments().iterator());

       return mav;
    }
    
    @RequestMapping("/workspaces")
    public ModelAndView getWorkSpace() {
        ModelAndView mav = new ModelAndView();

       documentHandler = new DocumentHandler();
       
       mav.setViewName("documentList");
       mav.addObject("documentList", documentHandler.getWorkSpace().iterator());
       
       return mav;
    }

    @RequestMapping(value="/document/{uiid}", method = RequestMethod.GET)
    public ModelAndView getDocument(@PathVariable String uiid) {
        ModelAndView mav = new ModelAndView();

    	documentHandler = new DocumentHandler();
       
    	mav.setViewName("document");
        mav.addObject("document", documentHandler.getDocument(uiid));

        return mav;
    }
    
    @RequestMapping(value = "/searchDocum.html", method = RequestMethod.GET)
    public ModelAndView searchDocum() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("searchDocum");
        mav.addObject("docum",new Docum());
    	
    	return mav;
    }

    @RequestMapping(value = "/getDocum.html", method = RequestMethod.POST)
    public ModelAndView getDocum(@ModelAttribute Docum docum) {
        ModelAndView mav = new ModelAndView();

    	documentHandler = new DocumentHandler();
        Document document = documentHandler.getDocument(docum);

        docum.setUiid(document.getId());
        
        docum.setTitle(document.getTitle());
        docum.setType(document.getType());
        //docum.setPath(document.getPath());

    	mav.setViewName("document");
    	mav.addObject("document", document);
    	
    	return mav;
    }

    @RequestMapping(value = "/newDocum.html", method = RequestMethod.GET)
    public ModelAndView newDocum() {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("newDocum");
        mav.addObject("docum",new Docum());

        return mav;
    }
    
    @RequestMapping(value = "/addDocum.html", method = RequestMethod.POST)
    public ModelAndView addDocum(@ModelAttribute Docum docum) {
        ModelAndView mav = new ModelAndView();

    	documentHandler = new DocumentHandler();
    	
		Document document = documentHandler.addDocument(docum);
		System.out.println("We got the doucment: "+document.getId());

		mav.setViewName("document");
		mav.addObject("document", document);

		return mav;
    }

    @RequestMapping(value = "/documForm.html", params = "Delete", method = RequestMethod.POST)
    public ModelAndView delDocum(@ModelAttribute Docum docum) {
        ModelAndView mav = new ModelAndView();

        System.out.println("Great, i am here on Delete:"+docum);
		System.out.println("Great, i am here on Delete:"+docum.getUiid());
		System.out.println("Great, i am here on Delete:"+docum.getTitle());
		System.out.println("Great, i am here on Delete:"+docum.getType());
		System.out.println("Great, i am here on Delete:"+docum.getPath());

		documentHandler = new DocumentHandler();
		docum.setUiid("34514bea-bfe1-4f99-a6cb-bba569d70a2f");
		documentHandler.deleteDocument(docum);
		mav.setViewName("document");
		mav.addObject("document", docum);

		return mav;
    }

    @RequestMapping(value = "/documForm.html", params = "Clone", method = RequestMethod.POST)
    public ModelAndView cloneDocum(@ModelAttribute Docum docum) {
        ModelAndView mav = new ModelAndView();

        System.out.println("Great, i am here in clone:"+docum);
		System.out.println("Great, i am here in clone:"+docum.getUiid());
		System.out.println("Great, i am here on clone:"+docum.getTitle());
		System.out.println("Great, i am here on clone:"+docum.getType());
		System.out.println("Great, i am here on clone:"+docum.getPath());

		mav.setViewName("document");
		mav.addObject("document", docum);

		return mav;
    }

    @RequestMapping(value = "/getDocAttachment.html", method = RequestMethod.POST)
    public void getDocAttachment(@ModelAttribute Docum docum) {

    	documentHandler = new DocumentHandler();
    	
		System.out.println("We got the doucment: "+docum.getUiid());
		Blob blob = documentHandler.getBlob(docum.getUiid());

		/*
        response.setContentType("image/jpg");

        OutputStream o = response.getOutputStream();

        o.write(imgData);
        */

    }
    
}
