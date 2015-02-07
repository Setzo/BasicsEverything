package euler80;

import java.math.BigDecimal;

public class Euler80 {

	public static void main(String[] args) {
		
		int sigma = 0;
		
		for(int i = 1; i < 100; i++) {
			if(Math.sqrt(i) % 1 != 0) {
				sigma += digitSigma(getSubstring(getValue(new BigDecimal(i))));
			}
		}
		
		System.out.println(sigma);
	}
	
	public static int digitSigma(String substr) {
		
		int sum = 0;
		
		for(int i = 0; i < substr.length(); i++) {
			sum += Character.getNumericValue(substr.charAt(i));
		}
		
		return sum;
	}
	
	public static String getSubstring(BigDecimal num) {
		
		return num.toString().substring(0, 101).replace(".", "");
	}
	
	public static BigDecimal getValue(BigDecimal num) {
		
		BigDecimal sqrtNum = new BigDecimal(Math.sqrt(num.doubleValue()));
		BigDecimal divider = new BigDecimal("0");
		
		while(!divider.equals(sqrtNum)) {
			
			divider = sqrtNum;
			sqrtNum = num.divide(divider, 100, BigDecimal.ROUND_FLOOR);
			sqrtNum = sqrtNum.add(divider);
			sqrtNum = sqrtNum.divide(new BigDecimal("2"), 100, BigDecimal.ROUND_FLOOR);
		}
		
		return sqrtNum;
	}
}
