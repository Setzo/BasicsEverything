package euler24;

public class Euler24 {

	private static int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	public static void main(String[] args) {

		for(int i = 0; i < 1E6 - 1; i++) {
			
			permutate();
		}
		
		for(int i : digits) {
			
			System.out.print(i);
		}
	}
	
	private static void permutate() {
		
		int index = getIndex();
		
		for (int i = 1; index + i < digits.length - i; i++) {
			
			int tmp = digits[index + i];
			digits[index + i] = digits[digits.length - i];
			digits[digits.length - i] = tmp;
		}
		
		int j = index + 1;
		
		while(digits[j] <= digits[index]) {
			
			j++;
		}
		
		int tmp = digits[index];
		digits[index] = digits[j];
		digits[j] = tmp;
	}
	
	private static int getIndex() {
		
		for(int index = digits.length - 2; true ;index--) {
			
			if(digits[index] < digits[index + 1]) {
				
				return index;
			}
		}
	}
}
