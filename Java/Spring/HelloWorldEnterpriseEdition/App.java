package tst;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class App {

    @SuppressWarnings( "resource" )
    public static void main(String[] args) {

        ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");

        User user = (User) context.getBean("user");

        user.sayHello();
    }

}
