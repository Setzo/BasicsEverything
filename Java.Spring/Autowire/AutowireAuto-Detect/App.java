package spring.testing.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "beans/annotations/beans.xml");

        Logger logger = (Logger) context.getBean("logger");

        logger.writeConsole("Hello there");
        logger.writeFile("Hi again");

        LoggerOptional loggerOptional = (LoggerOptional) context.getBean("loggerOptional");

        loggerOptional.writeConsole("Hello there");
        loggerOptional.writeFile("Hi again");

        LoggerJSR loggerJSR = (LoggerJSR) context.getBean("loggerJSR");

        loggerJSR.writeConsole("Hello there");
        loggerJSR.writeFile("Hi again");

        LoggerInject loggerInject = (LoggerInject) context.getBean("loggerInject");

        loggerInject.writeConsole("Hello there");
        loggerInject.writeFile("Hi again");

        ((ClassPathXmlApplicationContext) context).close();
    }

}
