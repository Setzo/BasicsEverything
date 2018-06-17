package euler26;

public class Euler26 {

    private static final int LIMIT = 1000;

    private static int by = 10;

    private static int max = 0;

    private static int maxLength = 0;

    public static void main(String[] args) {

        for (int i = Euler26.LIMIT; i > 1; i--) {

            if (Euler26.max >= i) {
                break;
            }

            Euler26.updateMax(i);
            System.gc();
        }

        System.out.printf("Number: %d, with the sequence length of: %d.",
                Euler26.max,
                Euler26.maxLength);
    }

    private static void updateMax(int actual) {

        int[] cycles = new int[actual];
        int num = 1;
        int position = 0;

        while (cycles[num] == 0 && num != 0) {
            cycles[num] = position;
            num = num * Euler26.by % actual;
            position++;
        }

        if (position - cycles[num] > Euler26.max) {
            Euler26.maxLength = position - cycles[num];
            Euler26.max = position;
        }
    }
}
