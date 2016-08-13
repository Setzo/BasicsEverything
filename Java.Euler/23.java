package euler23;

import java.util.LinkedList;

public class Euler23 {

	private static final int STARTING_POINT = 2;
	private static final int RANGE = 28123;
	
	private static int[] abundantNumbers;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		long sigma = 0;
		
		abundantNumbers = new int[RANGE + 1];

		for(int in : abundantNumbers) {
			in = 0;
		}
		
		for(int i = STARTING_POINT; i <= RANGE; i++) {
			
			if(isAbundant(i)) {
				abundantNumbers[i] = i;
			}
		}
		
		for(int i = 1; i <= RANGE; i++) {
			
			if(!isSumOf2AbundantNumbers(i)) {
				sigma += i;
			}
		}
		
		System.out.print(sigma);
	}
	
	private static boolean isSumOf2AbundantNumbers(int x) {
		
		for(int i = 0; i <= x; i++) {
			
			if(abundantNumbers[i] != 0 && abundantNumbers[x - i] != 0) {
				return true;
			}
		}
		
		return false;
	}
	
	private static LinkedList<Integer> divisors(int i) {

		LinkedList<Integer> divs = new LinkedList<Integer>();

		for (int j = 1; j <= i / 2; j++) {
			if (i % j == 0) {
				divs.add(j);
			}
		}

		return divs;
	}

	private static int sumDivisors(LinkedList<Integer> divs) {

		int sigma = 0;

		for (Integer i : divs) {
			sigma += i;
		}

		return sigma;
	}
	
	private static boolean isAbundant(int i) {
		
		return i < sumDivisors(divisors(i));
	}
}
