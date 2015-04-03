package euler21;

import java.util.LinkedList;

public class Euler21 {

	public static final int STARTING_POINT = 2;
	public static final int RANGE = 10000;
	
	public static void main(String[] args) {

		long sigma = 0;
		
		for(int i = STARTING_POINT; i < RANGE; i++) {
			
			LinkedList<Integer> startingList = divisors(i);
			int sigmaStartingList = sumDivisors(startingList);
			
			LinkedList<Integer> secondList = divisors(sigmaStartingList);
			int sigmaSecondList = sumDivisors(secondList);
			
			if(i == sigmaSecondList && sigmaStartingList != i) {
				sigma += i;
			}
		}
		
		System.out.println(sigma);
	}
	
	public static LinkedList<Integer> sumOfNumDivisors() {
		
		LinkedList<Integer> numDivs = new LinkedList<Integer>();
		
		for(int i = STARTING_POINT; i <= RANGE; i++) {
			numDivs.add(sumDivisors(divisors(i)));
		}
		
		return numDivs;
	}
	
	public static LinkedList<Integer> divisors(int i) {
		
		LinkedList<Integer> divs = new LinkedList<Integer>();
		
		for(int j = 1; j <= i / 2; j++) {
			if(i % j == 0) {
				divs.add(j);
			}
		}
		
		return divs;
	}
	
	public static int sumDivisors(LinkedList<Integer> divs) {
		
		int sigma = 0;
		
		for(Integer i : divs) {
			sigma += i;
		}
		
		return sigma;
	}
	
}
