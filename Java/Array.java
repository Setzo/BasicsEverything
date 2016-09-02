import java.util.Scanner;


public class Array {
    public static void main(String[] args) {

        final int n = 5;
        int[] tabX = new int[n];                 //Array na int'cie od 0 do n-1			//int[] tab = new tab {1,2,3}  tab.length = 3 tab[0] = 1
        Scanner X = new Scanner(System.in);                                                //								   tab[1] = 2 tab[2] = 3
        System.out.println(tabX.length);         //Wypisze 5
        for (int i = 0; i < tabX.length; i++) {         //length = n
            tabX[i] = X.nextInt();
            System.out.println(tabX[i]);
        }

        X.close();
    }
}
