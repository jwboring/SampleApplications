package com.crunchify.controller;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements ApplicationContextAware{
	
	private ApplicationContext ac;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ac = applicationContext;
		
		
		String[] names = ac.getBeanDefinitionNames();
		
		for(String name : names) {
			System.out.println(name);
		}
	}


}
