import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ShortCreater {
	public static void main(String[] args) {
		File file = new File("text.txt");
		int i=5;
		String c=null;
		StringBuilder sb = new StringBuilder();
		 
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
        	bw.write("hello ");
        	bw.write(i);					//Nie działa, trzeba zamienic na string
        	c+=i;
        	bw.write(c);
        	bw.write("sdsd");
        	bw.write("1 \n2");
        	bw.write("hello");
        	
        	sb.append("\nHello, Dear\n")
        	.append(54)
        	.append("\nnice number huh?");
        	bw.write(sb.toString());
        	
        	
        } catch (IOException e) {
            System.out.println("Unable to read file " + file.toString());
        }
	}
}
