import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class WritingObj {
	public static void main(String[] args) {
		
		System.out.println("Writing objects:");
		
		Person p1 = new Person("Person One");
		Person p2 = new Person("Person Two");
		
		System.out.println(p1);
		System.out.println(p2);
		
		File binaryFile= new File("people.bin");
		try (FileOutputStream fos = new FileOutputStream("people.bin")) {
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(p1);
			oos.writeObject(p2);
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
