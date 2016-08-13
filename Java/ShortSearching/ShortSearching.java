import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ShortSearching {
	public static void main(String[] args) {
		
		Tab tablica = new Tab();
		int i=0,j=0;
		File textFile = new File("byteObs.txt");
		
		try (Scanner sc = new Scanner(textFile)) {
			while (sc.hasNextLine()) {
				if(sc.hasNextInt()) {
					tablica.setTabElement(i, j, sc.nextShort());
					i++;
					tablica.setTabElement(i, j, sc.nextShort());
					i=0;
					j++;
					if(sc.hasNext("\\|")) {
						sc.skip(" \\|");
					}
				} else {
					sc.nextLine();
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + textFile.toString());
		}
		tablica.writeTab();
	}
}
