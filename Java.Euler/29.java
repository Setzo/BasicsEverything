package euler29;

import java.util.HashSet;
import java.util.Set;

public class Euler29 {

	private static final double LIMIT = 100.0;
	
	private static final double MINIMUM = 2.0;
	
	private static Set<Double> set;
	
	public static void main(String[] args) {

		Euler29.set = new HashSet<Double>();
		
		for(double i = Euler29.MINIMUM; i <= Euler29.LIMIT; i++) {
			
			for(double j = Euler29.MINIMUM; j <= Euler29.LIMIT; j++) {
				
				Euler29.set.add(Math.pow(i,  j));
			}
		}
		
		System.out.print(Euler29.set.size());
	}

}
