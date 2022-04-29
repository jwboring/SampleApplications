package com.crunchify.controller;


import java.util.Enumeration;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

/*
 * author: Crunchify.com
 * 
 */

@Controller
public class CrunchifyHelloWorld {
	
	@Autowired
	private ServletContext context;
	
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
		
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context);
		System.out.println(wac.getBeanDefinitionNames());
		
		Enumeration<String>  names = context.getAttributeNames();
		
		 while (names.hasMoreElements()) {
			    String attrName = names.nextElement();
			    System.out.println(attrName);
			  }
		
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);
	}
}