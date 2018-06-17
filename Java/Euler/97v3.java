package euler97;

import java.math.BigInteger;

public class Euler97v3 {

    public static void main(String[] args) {
        BigInteger x = new BigInteger("2");
        x =
                BigInteger.valueOf(2)
                        .modPow(BigInteger.valueOf(7830457), BigInteger.valueOf((long) Math.pow(10, 10)))
                        .multiply(BigInteger.valueOf(28433))
                        .subtract(BigInteger.valueOf(1))
                        .mod(BigInteger.valueOf((long) Math.pow(10, 10)));
        System.out.printf(x.toString());
    }
}
