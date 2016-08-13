package euler19;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Euler19 {

	public static void main(String[] args) {

		Calendar dateTime = new GregorianCalendar(1901, 0, 1, 0, 0, 0);

		int sundayCounter = 0;
		
		while(dateTime.get(1) < 2001) {
			
			if(dateTime.getTime().toString().split(" ")[0].equals("Sun") 
					&& dateTime.getTime().toString().split(" ")[2].equals("01")) {
				sundayCounter++;
			}
			
			dateTime.set(5, dateTime.get(5) + 1);
		}
		
		System.out.print(sundayCounter);
	}

}
