package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ApplicationContext context = new FileSystemXmlApplicationContext
	            ("./src/Beans.xml");
	    HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
	    obj.getMessage();
	}

}
