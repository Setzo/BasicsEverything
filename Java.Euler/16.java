package euler16;

import java.math.BigInteger;

public class Euler16 {
	public static void main(String[] args) {
		
		BigInteger x = new BigInteger("2");
		StringBuilder sb = new StringBuilder();
		int sum=0;
		x=x.pow(1000);
		sb.append(x.toString());
		for(int i=0; i<sb.length(); i++) {
			sum=sum+Character.getNumericValue(sb.charAt(i));
		}
		sb.delete(0, sb.length());
		System.out.println(sum);
	}
}
