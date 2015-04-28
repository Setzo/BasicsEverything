package pack1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bucket.Bucket;


public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");

		Bucket bucket = (Bucket)context.getBean("bucket");
		
		System.out.println(bucket);
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
