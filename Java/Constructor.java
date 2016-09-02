import java.util.Scanner;

class Imie {

    private String im;
    private int a, b;

    public Imie() {

        this(5, 7);                //wywoływanie 2 jeszcze nie zadeklarowanego konstruktora this(params) = forward w pascalu, ale tylko do construct.
        im = null;
        a = 5;
        b = a * 2 + 2;
        System.out.println(a + "  " + im + "  " + b);
    }

    public Imie(int a, int b) {

        im = null;
        this.a = a;
        this.b = b;
        System.out.println(a + "  " + im + "  " + b);
    }
}

public class Construct {
    public static void main(String[] args) {

        Imie im1 = new Imie();
        Scanner data = new Scanner(System.in);
        Imie im2 = new Imie(data.nextInt(), data.nextInt());

        data.close();
    }
}
