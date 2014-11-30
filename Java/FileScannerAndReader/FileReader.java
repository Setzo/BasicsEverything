import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class FileRead {
	public static void main(String[] args) {
		
		File file = new File("byteObs.txt");
		FileReader fr;
		BufferedReader br = null;
		
		try {
			
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String line;
			//StringBuilder sb = new StringBuilder("");
			
			while ( (line=br.readLine())!=null ) {
				System.out.println(line);
			}
	

		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find the file : " + file);
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unable to read file : " + file);
			//e.printStackTrace();
		}
		
		finally {
			try {
				
				br.close();					//BufferedReader automatycznie zamyka wszystko pod nim
			} catch (IOException e) {
				
				System.out.println("Couldn't close the file: " + file);
				//e.printStackTrace();
			} catch (NullPointerException e) {
				
				//Never reached the file
				//e.printStackTrace();
			}
		}
	}
}
