package spring.testing.annotations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component( "fileWriter" )
@Qualifier( "toFile" )
public class FileWriter implements LogWriter {

    public void write(String text) {
        System.out.println("Write to file: " + text);

    }

}
