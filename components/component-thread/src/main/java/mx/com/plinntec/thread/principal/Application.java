package mx.com.plinntec.thread.principal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mx.com.plinntec.thread.start.Starter;

public class Application {
	public static void main(String args[]){
	
	  ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-thread-components.xml");
	         Starter starter = (Starter) context.getBean("Starter");
	         starter.start();
	}

}
