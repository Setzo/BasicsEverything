import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


public class ReadObj {
    public static void main(String[] args) {
        System.out.println("Reading objects :");

        File binaryFile = new File("people.bin");
        try (FileInputStream fis = new FileInputStream(binaryFile)) {

            ObjectInputStream ois = new ObjectInputStream(fis);

            Person p1 = (Person) ois.readObject();
            Person p2 = (Person) ois.readObject();

            ois.close();

            System.out.println(p1.toString());
            System.out.println(p2.toString());

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
