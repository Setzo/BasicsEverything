import java.util.Scanner;


public class Switch {
    public static void main(String[] args) {

        Scanner data = new Scanner(System.in);
        int x = data.nextInt();
        switch (x) {
            case 1:
                System.out.println(x);
                break;
            case 2:
                System.out.println(x + x);
                break;
            case 3:
                System.out.println(x * x);
                break;
            case 4:
                System.out.println(x * x * x);
                break;
            default:
                System.out.println("def");
                break;
        }
        data.close();
    }
}
