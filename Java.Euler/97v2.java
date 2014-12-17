package euler97;

import java.math.BigInteger;

public class Euler97v2 {

	public static void main(String[] args) {
		BigInteger x = new BigInteger("2");
		x = BigInteger.valueOf(2);
		x = x.modPow(BigInteger.valueOf(7830457), BigInteger.valueOf((long)Math.pow(10,10)));
		x = x.multiply(BigInteger.valueOf(28433));
		x = x.subtract(BigInteger.valueOf(1));
		x = x.mod(BigInteger.valueOf((long)Math.pow(10,10)));
		System.out.printf(x.toString());
	}
}
