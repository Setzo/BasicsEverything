package euler34;


public class Euler34 {

    public static void main(String[] args) {

        int a = 0;

        for (long i = 3; i < 10000000; i++) {

            if (countFSum(i) == i) {
                a += i;
            }
        }

        System.out.print(a);

    }

    public static long countF(long a) {

        if (a < 0) {
            return -1;
        }
        if (a == 0) {
            return 1;
        }
        if (a == 1) {
            return a;
        }
        return countF(a - 1) * a;
    }

    public static long countFSum(long a) {

        long sum = 0;

        while (a != 0) {
            sum += countF(a % 10);
            a /= 10;
        }

        return sum;
    }
}
