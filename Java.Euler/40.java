package euler40;

public class Euler40 {

	public static void main(String[] args) {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i < 1E6; i++) {
			sb.append(i);
		}
		
		int result = Character.getNumericValue(sb.charAt(0))
				* Character.getNumericValue(sb.charAt(9))
				* Character.getNumericValue(sb.charAt(99))
				* Character.getNumericValue(sb.charAt(999))
				* Character.getNumericValue(sb.charAt(9999))
				* Character.getNumericValue(sb.charAt(99999))
				* Character.getNumericValue(sb.charAt(999999));
		
		System.out.print(result);
	}
}
