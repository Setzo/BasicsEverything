package euler15;

public class Euler15 {

    public static void main(String[] args) {

        System.out.print(count(40, 20));
    }

    public static long count(int movement, int gridDimension) {

        long newtonSymbol = 1;

        for (int i = 0; i < gridDimension; i++) {

            newtonSymbol *= (movement - i);
            newtonSymbol /= (i + 1);
        }
        return newtonSymbol;
    }
}
