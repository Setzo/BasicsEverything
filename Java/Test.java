package euler4;

public class Test {

    public static void main(String[] args) {

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        sb1.append("HeleH");

        sb2.append(sb1.toString());
        sb2.reverse();
        sb1.substring((int) Math.ceil(sb1.length() / 2));
        sb2.substring((int) Math.ceil(sb2.length() / 2));

        if (sb1.substring((int) Math.ceil(sb1.length() / 2)).equals(sb2.substring((int) sb2.length() / 2))) {
            System.out.println("Tak");
        }

        System.out.println(sb1);
        System.out.println(sb2);
    }
}
