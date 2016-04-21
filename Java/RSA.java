package com.wpruszak.crypto.rsa;

import java.math.BigInteger;

/**
 * {@author Wojciech Pruszak} <info@Wpruszak.com> on 21.04.16.
 */

public class RSA {

    private static final int AGENT_NUMBER = 975;

    private static final String WORD = "wiosna";

    private static final int[] P_TABLE = {11, 13, 17, 19, 11, 13, 17, 19, 11, 13, 17, 19, 11, 13, 17, 19};
    private static final int[] Q_TABLE = {109, 97, 97, 97, 101, 101, 101, 101, 103, 103, 103, 103, 107, 107, 107, 107};

    private static int getCoprime(int q, int totient) {

        for (int cnt = q + 1; ; cnt++) {

            if (!isCoprime(totient, cnt)) {
                continue;
            }

            return cnt;
        }
    }

    private static boolean isCoprime(int num1, int num2) {

        int max = Math.max(num1, num2);

        for (int counter = 2; counter <= max / 2; counter++) {
            if (num1 % counter == 0 && num2 % counter == 0) {
                return false;
            }
        }

        return true;
    }

    private static int getPrivateKey(int publicKey, int totient) {

        int privateKey = 1;

        while ((privateKey * publicKey) % totient != 1) {
            privateKey++;
        }

        return privateKey;
    }

    private static int[] asciiValues(String word) {

        int[] ascii = new int[word.length()];
        char[] letters = word.toCharArray();

        for (int counter = 0; counter < word.length(); counter++) {

            ascii[counter] = (int) letters[counter];
        }

        return ascii;
    }

    private static int[] encrypt(String word, int publicKey, int pxq) {

        int[] encrypted = new int[word.length()];
        char[] letters = word.toCharArray();

        for (int counter = 0; counter < word.length(); counter++) {

            encrypted[counter] =
                Integer.parseInt(
                    BigInteger.valueOf((long) letters[counter])
                        .modPow(
                            BigInteger.valueOf(publicKey),
                            BigInteger.valueOf(pxq)
                        ).toString()
                );
        }

        return encrypted;
    }

    private static int[] decrypt(String encrypted, int privateKey, int pxq) {

        String[] toDecrypt = encrypted.split("\\s");
        int[] decrypted = new int[toDecrypt.length];

        for(int counter = 0; counter < toDecrypt.length; counter++) {
            decrypted[counter] =
                Integer.parseInt(
                    BigInteger.valueOf(Integer.parseInt(toDecrypt[counter]))
                        .modPow(
                            BigInteger.valueOf(privateKey),
                            BigInteger.valueOf(pxq)
                        ).toString()
            );
        }

        return decrypted;

    }

    private static String numericToAscii(String numeric) {

        String[] toAscii = numeric.split("\\s");
        StringBuilder sb = new StringBuilder();

        for(int counter = 0; counter < toAscii.length; counter++) {

            sb.append((char)((byte) Integer.parseInt(toAscii[counter])));
        }

        return sb.toString();
    }

    public static void main(String args[]) {

        int p = P_TABLE[AGENT_NUMBER % P_TABLE.length];
        int q = Q_TABLE[AGENT_NUMBER % Q_TABLE.length];

        int pxq = p * q;

        // http://stackoverflow.com/questions/20925656/how-to-compute-eulers-totient-function-%CF%86-in-java
        int totient = (p - 1) * (q - 1);

        int publicKey = getCoprime(q, totient);

        int privateKey = getPrivateKey(publicKey, totient);

        System.out.println("Agent: " + AGENT_NUMBER);
        System.out.println("Word: " + WORD);
        System.out.println("P: " + p);
        System.out.println("Q: " + q);
        System.out.println("PxQ (N): " + pxq);
        System.out.println("Totient (φ[P * Q]): " + totient);
        System.out.println("Public key (E): " + publicKey);
        System.out.println("Private key (D): " + privateKey);

        int[] asciiVals = asciiValues("wiosna");
        System.out.print("Before: ");
        for (int i : asciiVals) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] encrypted = encrypt("wiosna", publicKey, pxq);
        StringBuilder toDec = new StringBuilder();
        for (int i : encrypted) {
            toDec.append(i);
            toDec.append(" ");
        }
        System.out.println("Encrypted (Numeric): " + toDec.toString());
        System.out.println("Encrypted (Text): " + numericToAscii(toDec.toString()));

        StringBuilder toEnc = new StringBuilder();
        int[] decrypted = decrypt(toDec.toString(), privateKey, pxq);
        for (int i : decrypted) {
            toEnc.append(i);
            toEnc.append(" ");
        }
        System.out.println("Decrypted (Numeric): " + toEnc.toString());
        System.out.println("Decrypted (Text): " + numericToAscii(toEnc.toString()));
    }
}
