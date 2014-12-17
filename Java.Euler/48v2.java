package euler48;

import java.math.BigInteger;

public class Euler48 {
	public static void main(String[] args) {
		
		BigInteger x = new BigInteger("1");
		BigInteger y = new BigInteger("0");
		long t=System.currentTimeMillis();
		for(int i=1; i<=1000; i++) {
			x=BigInteger.valueOf(i);
			x=x.modPow(BigInteger.valueOf(i), BigInteger.valueOf((long)Math.pow(10, 10)));
			y=y.add(x);
		}
		y=y.mod(BigInteger.valueOf((long)Math.pow(10, 10)));
		System.out.println(System.currentTimeMillis()-t + " ms.");
		System.out.print(y.toString());
	}
}
