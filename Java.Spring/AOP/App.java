package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/beans/beans.xml");
		
		SayThings sayThings = (SayThings)context.getBean("sayThings");
		
		sayThings.sayHi();
		sayThings.sayHi(5);
		sayThings.sayHi("Hello");
		sayThings.doSth();
		
		Car car = (Car)context.getBean("car");
		
		car.type(3);
		
		((ClassPathXmlApplicationContext)context).close();
	}
}
