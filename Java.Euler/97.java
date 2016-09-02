package euler97;

import java.math.BigInteger;

public class Euler97 {

    public static void main(String[] args) /*throws InterruptedException*/ {
        BigInteger x = new BigInteger("2");
        StringBuilder sb = new StringBuilder("");
        x = BigInteger.valueOf(2);
        x = x.pow(7830457);
        sb.append(x.toString());
        //System.out.println(sb.toString().substring(sb.length() - 10, sb.length()));
        Long res = Long.parseLong(sb.toString().substring(sb.length() - 10, sb.length()));
        sb.delete(0, sb.length());
        res = (res * 28433) + 1;
        System.out.printf("%s", res.toString().substring(res.toString().length() - 10), res.toString());
    }
}
