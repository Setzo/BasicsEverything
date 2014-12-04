import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class ByteS {
	public static void main(String[] args) {
		File byteObs = new File ("byteObs.txt");
		Tab tablica = new Tab();
		int x=0;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(byteObs))) {
			StringBuilder sb = new StringBuilder("");
			byte byteVal;
			for(int i=0;i<10000;i++) {
				if((i%50==0)&&(i!=0)) {
					sb.append("\n");
				}
				byteVal=(byte)i;
				if(byteVal<-99) {
					sb.append(" ").append(byteVal);
				}
				else if(byteVal>99 || byteVal<-9) {
					sb.append("  ").append(byteVal);
				}
				else if(byteVal>9 || byteVal<0) {
					sb.append("   ").append(byteVal);
				}
				else {
					sb.append("    ").append(byteVal);
				}			
			}
			bw.write(sb.toString());
		} catch (IOException e) {
            System.out.println("Unable to read file " + byteObs.toString());
            //e.printStackTrace();
        } catch (Exception e ) {
        	System.out.println("Unknown error");
        	//e.printStackTrace();
        }
		try(Scanner input = new Scanner(byteObs)) {
			while(input.hasNextInt()) {
				tablica.setTabElement(x, input.nextShort());
				x++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found : " + byteObs.toString());
			//e.printStackTrace();
		}
		x=0;
		while(tablica.hasNxt(x)) {
			if(x%50==0) {
				System.out.printf("\n");
			}
			System.out.printf("%6d ",tablica.getTabElement(x));
			x++;
		}
	}
}
