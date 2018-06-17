package euler2;

public class Euler2 {
    public static void main(String[] args) {

        int a = 1, b = 2, sum = 0, tmp;
        while (a <= 4000000 && b <= 4000000) {
            if (b % 2 == 0) {
                sum = sum + b;
            }
            tmp = b;
            b = b + a;
            a = tmp;

        }
        System.out.println(sum);
    }
}
