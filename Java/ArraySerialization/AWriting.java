import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class WritingObj {
    public static void main(String[] args) {

        System.out.println("Writing objects:");

        Person[] arrayP =
                {new Person("Person One"), new Person("Person Two"), new Person("Person Three"), new Person("Person Four")};

        ArrayList<Person> arrayL = new ArrayList<Person>(Arrays.asList(arrayP));

        File binaryFile = new File("people.bin");
        try (FileOutputStream fos = new FileOutputStream("people.bin")) {

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(arrayP);
            oos.writeObject(arrayL);

            oos.writeInt(arrayL.size());

            for (Person osoba : arrayL) {
                oos.writeObject(osoba);
            }

            oos.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + binaryFile.toString());
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Couldn't read the file: " + binaryFile.toString());
            //e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown error: ");
            e.printStackTrace();
        }
    }
}
