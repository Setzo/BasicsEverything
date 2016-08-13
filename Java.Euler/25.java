package euler25;

import java.math.BigInteger;

public class Euler25 {
	public static void main(String[] args) {
		BigInteger x = BigInteger.ONE;
		BigInteger y = BigInteger.ONE;
		BigInteger tmp = BigInteger.ONE;
		StringBuilder sb = new StringBuilder();
		long term = 2;
		boolean isItCorrect=false;
		while(!isItCorrect) {
			tmp=x;
			x=x.add(y);
			y=tmp;
			sb.append(x.toString());
			if(sb.length()>=1000) {
				isItCorrect = true;
			}
			sb.delete(0, sb.length());
			term++;
		}
		System.out.println("term : " + term);
	}
}
