package euler4;

public class Euler4 {
    public static void main(String[] args) {

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        Integer max = 0, res;
        for (Integer i = 100; i <= 999; i++) {
            for (Integer j = 100; j <= 999; j++) {
                res = i * j;
                sb1.append(res.toString());
                sb2.append(sb1);
                sb2.reverse();
                if (sb1.substring((int) Math.ceil(sb1.length() / 2)).equals(sb2.substring((int) sb2.length() / 2))) {
                    if (res > max) {
                        max = res;
                    }
                }
                sb1.delete(0, sb1.length());
                sb2.delete(0, sb2.length());
            }
        }
        System.out.println(max);
    }
}
