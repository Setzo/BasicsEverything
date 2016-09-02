import java.util.Scanner;


public class Matrix {
    public static void main(String[] args) {

        Scanner data = new Scanner(System.in);
        final int w = 3, k = 2;
        //int[][] x = {							//Tworzy tablice 3x4, bo a1=[0,0]
        //		{1,12,3},						//x[0,0]=1  x[0,1]=12 x[0,2]= 3
        //		{45,4,1,0},						//x[1,0]=45 x[1,1]=4  x[1,2]= 1  x[1,3]=0
        //		{2,3}							//x[2,0]=2  x[2,1]=3
        //};
        int[][] matX = new int[w][k];            //tab[w,k]

        for (int i = 0; i < matX.length; i++) {
            for (int j = 0; j < matX[i].length; j++) {
                matX[i][j] = data.nextInt();
            }
        }

        for (int i = 0; i < matX.length; i++) {
            for (int j = 0; j < matX[i].length; j++) {
                System.out.println(matX[i][j]);
                if (j == matX[i].length - 1) {
                    System.out.println(System.lineSeparator());
                }
            }
        }
        data.close();
    }
}
