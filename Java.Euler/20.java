package euler20;

import java.math.BigInteger;

public class Euler20 {
	public static void main(String[] args) {
		BigInteger x = BigInteger.ONE;
		BigInteger c = BigInteger.ZERO;
		int sum=0;
		StringBuilder sb = new StringBuilder("");
		for(long i=2; i<=100; i++) {
			c=BigInteger.valueOf(i);
			x=x.multiply(c);
		}
		sb.append(x.toString());
		for(int i=0; i<sb.length(); i++) {
			sum=sum + Character.getNumericValue(sb.toString().charAt(i));
		}
		sb.delete(0, sb.length());
		System.out.println(sum);
	}
}
