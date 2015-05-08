package euler24;

public class Euler24 {

	private static int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	public static void main(String[] args) throws InterruptedException{

		for(int i = 0; i < 1E6 - 1; i++) {
			permutate();
		}
		
		for(int i : digits) {
			
			System.out.print(i);
		}
	}
	
	@SuppressWarnings("unused")
	private static void showDigits() {
		
		for(int i : digits) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
	
	private static void permutate() {
		
		int index = getIndex();
		
		for (int j = 1; index + j < digits.length - j; j++) {
			
			//showDigits();
			int tp = digits[index + j];
			digits[index + j] = digits[digits.length - j];
			digits[digits.length - j] = tp;
			//showDigits();
		}
		
		int j = index + 1;
		
		while(digits[j] <= digits[index]) {
			j++;
		}
		
		//showDigits();
		int tp = digits[index];
		digits[index] = digits[j];
		digits[j] = tp;
		//showDigits();
	}
	
	private static int getIndex() {
		
		for(int index = digits.length - 2; true ;index--) {
			
			if(digits[index] < digits[index + 1]) {
				
				return index;
			}
		}
	}
}
