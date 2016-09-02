package spring.testing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "beans/beans.xml");

        LoggerByType loggerbyType = (LoggerByType) context.getBean("loggerbytype");

        loggerbyType.writeConsole("Hello there");
        loggerbyType.writeFile("Hi again");

        LoggerByName loggerByName = (LoggerByName) context.getBean("loggerbyname");

        loggerByName.writeConsole("Hello there");
        loggerByName.writeFile("Hi again");

        LoggerByConstructor loggerByConstructor = (LoggerByConstructor) context.getBean("loggerbyconstructor");

        loggerByConstructor.writeConsole("Hello there");
        loggerByConstructor.writeFile("Hi again");

        LoggerByName loggerByDefault = (LoggerByName) context.getBean("loggerbydefault");

        loggerByDefault.writeConsole("Hello there");
        loggerByDefault.writeFile("Hi again");

        ((ClassPathXmlApplicationContext) context).close();
    }

}
