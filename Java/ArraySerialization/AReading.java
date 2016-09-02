import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class ReadObj {
    public static void main(String[] args) {
        System.out.println("Reading objects :");

        File binaryFile = new File("people.bin");
        try (FileInputStream fis = new FileInputStream(binaryFile)) {

            ObjectInputStream ois = new ObjectInputStream(fis);

            Person[] arrayP = (Person[]) ois.readObject();
            @SuppressWarnings( "unchecked" )
            ArrayList<Person> arrayL = (ArrayList<Person>) ois.readObject();

            for (Person osoba : arrayP) {
                System.out.println(osoba.toString());
            }

            System.out.println();

            for (Person osoba : arrayL) {
                System.out.println(osoba.toString());
            }

            System.out.println();

            int n = ois.readInt();
            for (int i = 0; i < n; i++) {                //for(int i=0; i < ois.readInt(); i++) nie dziala, bo readInt po kazdym przejsciu
                Person osoba = (Person) ois.readObject();        //fora szuka nowego inta.
                System.out.println(osoba.toString());
            }

            ois.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + binaryFile.toString());
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Couldn't read the file: " + binaryFile.toString());
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Couldnt find the class: " + Person.class.getCanonicalName());
            //e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown error: ");
            e.printStackTrace();
        }
    }
}
