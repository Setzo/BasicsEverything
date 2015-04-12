package tst;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("tst/beans/beans.xml");
		
		User user = (User)context.getBean("user");
		
		user.sayHello();
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
