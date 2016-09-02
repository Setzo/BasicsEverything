package demo1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class App {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("byteObs.txt");

        FileReader fr = new FileReader(file);
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
