package euler13;

import java.math.BigInteger;
import java.util.Scanner;

public class Euler13 {
    public static void main(String[] args) {

        BigInteger sum = BigInteger.ZERO;
        BigInteger x = null;

        try (Scanner data = new Scanner(Euler13.class.getResourceAsStream("euler13.txt"))) {

            while (data.hasNextLine()) {
                x = new BigInteger(data.nextLine());
                sum = sum.add(x);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        System.out.print(sum.toString().substring(0, 10));
    }
}
