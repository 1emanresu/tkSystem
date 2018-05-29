package com.tkSystem.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebApplicationInitializer implements WebApplicationInitializer {
	final Logger logger = Logger.getLogger("com.tkSystem.system");
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void onStartup(ServletContext container) {
		System.out.println("MyWebApplicationInitializer");
	/*	logger.info(String.format("Current time: %s", dateFormat.format(new Date())));
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.setConfigLocation("/WEB-INF/dispatcherServlet-servlet.xml");
		ServletRegistration.Dynamic registration = container.addServlet("dispatcher",
				new DispatcherServlet(appContext));
		registration.setLoadOnStartup(1);
		registration.addMapping("/");*/
		
	}
}