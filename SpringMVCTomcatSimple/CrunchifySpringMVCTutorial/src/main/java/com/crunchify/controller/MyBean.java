package com.crunchify.controller;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

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
		
		InetAddress ipAddress = null;
		String hostname = null;
		try {
			ipAddress = InetAddress.getLocalHost();
			hostname = ipAddress.getHostName();
		} catch (UnknownHostException e) {
			System.out.println("got UnknownHostException");
		}
		
		Properties sysProps = System.getProperties();
		System.out.println(String.format("InetAddress: %s", ipAddress));
		System.out.println(String.format("hostname: %s", hostname));
		System.out.println(String.format("JRE Vendor: %s", sysProps.getProperty("java.vendor")));
		System.out.println(String.format("JRE Version: %s", sysProps.getProperty("java.version")));
		System.out.println(String.format("Classpath:\n%s", sysProps.getProperty("java.class.path").replaceAll(";", "\n")));

		printSpringBeans(ac);
	}
	
	
	
	public void printSpringBeans(ApplicationContext ctx) {
		String[] beans = ctx.getBeanDefinitionNames();
		int loadedTotalBeans = ctx.getBeanDefinitionCount();
 
		String printFormat = getBeanFormat(beans);
 
		printStatement(String.format("------------- Total Loaded Spring Beans: %d ------------- \n", loadedTotalBeans));
 
		int count = 1;
		for (String bean : beans) {
			Class<?> beanType = ctx.getType(bean);
			Package beanPackage = beanType.getPackage();
			if (!beanPackage.getName().startsWith("org.springframework") && !beanPackage.getName().startsWith("java"))
				printStatement(String.format(printFormat, count++, bean, beanType.getName(), beanPackage));
		}
		printStatement(String.format("---------------------------------------------------------"));
	}
 
	private void printStatement(String value) {
		System.out.println(value);
	}
 
	private String getBeanFormat(String[] beans) {
		int namespace = betterAlignment(beans);
		int typespace = (beans.length < 100) ? 2 : 3;
		return String.format("%%%dd: Bean Name: %%-%ds \n - Bean Type: %%s \n - Package: %%s \n", typespace, namespace);
	}
 
	private int betterAlignment(String[] beans) {
		int length = 0;
 
		for (String myStr : beans) {
			if (myStr.length() > length)
				length = myStr.length();
		}
		return length;
	}

}
