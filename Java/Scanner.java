import java.util.Scanner;

public class ReadLn {
	public static void main(String[] args){
		
		int a,b,value;
		Scanner x = new Scanner(System.in);
		System.out.println("Podaj x : ");
		String fx = x.nextLine();
		value = x.nextInt();
		a=5;
		b=value+a;
		x.close(); 									//* SCANNER CLOSE */
		System.out.println(b + " SPACE " + fx);
	}
	
}
