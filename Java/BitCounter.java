import java.math.BigInteger;

public class BitCounter {

    public static void main(String[] args) {

//		Our strings to compare against each other.
        String wordOne = "a2fde7186171a34b6284d61456c41300";
        String wordTwo = "7c34f9505956c778000fe85b9b73dd61";

//		Get the number of overall bits.
        System.out.println("Bity: " + getNumberOfBits(wordOne, true));

//		Either hex to bin or ascii to bin.
        String binOne = hexToBinary(wordOne);
        String binTwo = hexToBinary(wordTwo);

//		 Bits comparison, returns the number of same bits on same places and number of
//		 changed bits on same places.
        int[] difference = calculateDiff(binOne, binTwo);

//		Returns percent of bits changed / unchanged.
        double[] differencePercent = diffInPercent(difference);

//		Write out the result.
        System.out.printf("%d%s%d\n", difference[0], " same/diff ", difference[1]);
        System.out.printf("%.2f%%%s%.2f%%\n", differencePercent[0], " same/diff ", differencePercent[1]);
    }

    /**
     * Returns the number of bits a string contains.
     * Depending on if the string is a hexadecimal string or
     * ascii string result will differ.
     *
     * @param word
     * @param isHex hex(true) or ascii(false)
     * @return bits
     */
    private static int getNumberOfBits(String word, boolean isHex) {

        return isHex ? word.length() * 4 : word.length() * 8;
    }

    /**
     * Calculate the percent of different / same bits.
     *
     * @param differ
     * @return percentile difference in bits
     */
    private static double[] diffInPercent(int[] differ) {

        double same = differ[0];
        double diff = differ[1];
        double all = same + diff;

        double diffPercent = diff / (all / 100f);

        return new double[] {round(100f - diffPercent, 2), round(diffPercent, 2)};
    }

    /**
     * Round {@code value} to {@code places} after a comma (or dot).
     *
     * @param value
     * @param places
     * @return rounded value
     */
    private static double round(double value, int places) {

        if (places < 0) {
            throw new IllegalArgumentException("We are still in the real world.");
        }

        long factor = (long) Math.pow(10, places);

        return (double) Math.round(value * factor) / factor;
    }

    /**
     * Compare the characters of two strings.
     * Return the number of same / different characters in both
     * strings.
     *
     * @param wordOne
     * @param wordTwo
     * @return difference
     */
    private static int[] calculateDiff(String wordOne, String wordTwo) {

        if (wordOne.length() != wordTwo.length()) {
            throw new IllegalArgumentException("Lengths must match.");
        }

        int same = 0;

        for (int counter = 0; counter < wordOne.length(); counter++) {
            if (wordOne.charAt(counter) == wordTwo.charAt(counter)) {
                same++;
            }
        }

        return new int[] {same, wordOne.length() - same};
    }

    /**
     * Convert hexadecimal string to binary string.
     *
     * @param hex
     * @return bin
     */
    private static String hexToBinary(String hex) {
        return new BigInteger("1" + hex, 16).toString(2).substring(1);
    }

    /**
     * Convert ascii string to binary string.
     *
     * @param word
     * @return bin
     */
    private static String asciiToBinary(String word) {

        byte[] bytes = word.getBytes();
        StringBuilder binary = new StringBuilder();

        for (byte b : bytes) {
            int val = b;
            for (int counter = 0; counter < 8; counter++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }

        return binary.toString();
    }
}
