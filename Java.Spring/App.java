package tst;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("tst/beans/beans.xml");
		
		User user = (User)context.getBean("user");
		
		user.sayHello();
		
		System.out.println(user);

		Address adderss = (Address)context.getBean(tst.Address.class);
		
		System.out.println(adderss);
		
		user.setCount(45656);
		
		User user1 = (User)context.getBean("user");
		
		System.out.println(user1);
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
