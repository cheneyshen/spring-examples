package com.tutorialspoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		HelloWorld objA = (HelloWorld)context.getBean("helloWorld");
		objA.getMessage1();
		objA.getMessage2();
		HelloChina objB = (HelloChina)context.getBean("helloChina");
		objB.getMessage1();
		objB.getMessage2();
		objB.getMessage3();
	}

}
