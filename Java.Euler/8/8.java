package euler8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Euler8 {
	
	static int[] x = new int[1000]; 
	public static void main(String[] args) {
		File txt = new File("text.txt");
		StringBuilder sb = new StringBuilder();
		long max=1, act=1;
		//int[] max1 = new int[13];
		//int[] act1 = new int[13];
		try(Scanner data = new Scanner(txt)) {
			sb.append(data.nextLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<=999; i++) {
			x[i]=Character.getNumericValue(sb.charAt(i));
		}
		
		for(int i=0; i<=987; i++) {
			for(int j=0; j<13; j++) {
				act = x[i+j]*act;
				//act1[j] = x[i+j];
				if(act>max) {
					max = act;
					//max1 = act1;
				}
			}
			act=1;
		}
		//for (int i=0; i<13; i++) {
		//	System.out.printf("%d ", max1[i]);
		//}
		System.out.println(max);
	}
}
