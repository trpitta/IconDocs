package com.icondocs.dm.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.icondocs.dm.handler.AuthenticationHandler;
import com.icondocs.dm.web.model.User;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

	/*
	 * Classname reference for the log statements.
	 */
	private static final String className = AuthenticationController.class.toString();     	

	/*
	 * logger instance.
	 */
    private static final Log log = LogFactory.getLog(AuthenticationController.class);

    private static final boolean isDebug = log.isDebugEnabled();

    private static final boolean isInfo = log.isInfoEnabled();

    private AuthenticationHandler authenticationHandler;

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public ModelAndView loginForm(Model model) {
        ModelAndView mav = new ModelAndView();

        mav.setViewName("login");
        mav.addObject("user",new User());
    	model.addAttribute(new User());
    	
    	return mav;
    }
    
    @RequestMapping(value = "/authenticate.html", method = RequestMethod.POST)
    public ModelAndView authentication(@ModelAttribute User user, Model model) {
      
    	System.out.println("Hey I am here authentication():"+user);
    	System.out.println("Hey I am here authentication:"+user.getUserName());
    	System.out.println("Hey I am here authentication:"+user.getPassword());
   	
    	authenticationHandler = new AuthenticationHandler(user.getUserName(), user.getPassword());
    	
		if(!authenticationHandler.authenticate(user.getUserName(), user.getPassword())){
			System.out.println("returned false");
		}
		ModelAndView modelAndView = new ModelAndView("home");

		return modelAndView;
    }
    
}
