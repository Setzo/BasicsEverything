package euler48;

import java.math.BigInteger;

public class Euler48 {
    public static void main(String[] args) {

        BigInteger x = new BigInteger("1");
        BigInteger y = new BigInteger("0");
        //System.out.println(y);
        for (int i = 1; i <= 1000; i++) {
            x = BigInteger.valueOf(i);
            x = x.pow(i);
            y = y.add(x);
            /*try {
                Thread.sleep(100);
			} catch (Exception e) {
			}
			System.out.println(x.toString() + "  " + y.toString());*/
        }
        System.out.print(y.toString().substring(y.toString().length() - 10, y.toString().length()));
    }
}
