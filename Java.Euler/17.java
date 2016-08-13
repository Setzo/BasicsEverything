package euler17;


public class Euler17 {
	
	private static final String digits[] = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	private static final String teensNums[] = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	private static final String tensNums[] = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	private static final String hundred = "hundred";	// 7
	private static final String thousand = "thousand";	// 8
	private static final String and = "and";			// 3
			
	public static void main(String[] args) {

		int sum = 0;
		
		for (int i = 1; i <= 1000; i++) {
			
			sum += countLetter(i);
		}
		System.out.print(sum);
	}
	
	public static int countLetter(int n) {
		
		String stringN = String.valueOf(n);
		int len = stringN.length();
		
		if (len < 2) {
			return digits[n - 1].length();
			
		} else if (len < 3) {
			if (n < 20) {
				return teensNums[n % 10].length();
				
			} else if (n % 10 == 0) {
				return tensNums[n / 10 - 2].length();
				
			} else {
				return digits[Character.getNumericValue(stringN.charAt(1) - 1)].length()
						+ tensNums[Character.getNumericValue(stringN.charAt(0)) - 2].length();
			}
			
		} else if (len < 4) {
			if (n % 100 == 0) {
				return digits[n / 100 - 1].length() + hundred.length();
				
			} else if (n % 100 < 10) {
				return digits[(n - (n % 100)) / 100 - 1].length()
						+ hundred.length()
						+ and.length()
						+ digits[n % 100 - 1].length();
				
			} else if (n % 100 < 20) {
				return digits[(n - (n % 100)) / 100 - 1].length()
						+ hundred.length()
						+ and.length()
						+ teensNums[n % 10].length();
				
			} else {
				if(n % 10 != 0) {
					return digits[(n - (n % 100)) / 100 - 1].length()
							+ hundred.length()
							+ and.length()
							+ tensNums[((n - (Character.getNumericValue(stringN.charAt(0)) * 100)) - (n % 10)) / 10 - 2].length()
							+ digits[n % 10 - 1].length();
					
				} else {
					return digits[(n - (n % 100)) / 100 - 1].length()
							+ hundred.length()
							+ and.length()
							+ tensNums[((n - (Character.getNumericValue(stringN.charAt(0)) * 100)) - (n % 10)) / 10 - 2].length();
				}
			}
			
		} else {
			return digits[0].length() + thousand.length();
		}
	}
}
