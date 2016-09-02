import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileSc {
    public static void main(String[] args) throws FileNotFoundException {

        String byteObs = "C:/Users/Setzo/Desktop/byteObs.txt";
        File textFile = new File(byteObs);
        Scanner input = new Scanner(textFile);

        while (input.hasNextLine()) {
            String l = input.nextLine();
            System.out.println(l);
        }

        input.close();
    }
}
