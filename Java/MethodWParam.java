import java.util.Scanner;

class multiply {

    public int dod(int x, int y) {
        return x + y;
    }
}

public class MethWParam {
    public static void main(String[] args) {


        Scanner data = new Scanner(System.in);
        multiply funkcja = new multiply();
        System.out.println(funkcja.dod(data.nextInt(), data.nextInt()));
        data.close();

    }
}
