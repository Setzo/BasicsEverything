class Dod {

    public int dod(int a, int b) {
        return a + b;
    }

    public String toString() {
        return "Normal class";
    }
}

interface IInterface {
    public void sayHi();
}

public class AnonClass {
    public static void main(String[] args) {

        Dod x = new Dod() {
            @Override
            public int dod(int a, int b) {
                return a - b;
            }

            @Override
            public String toString() {
                return "Anonymous class injected";
            }
        };
        Dod y = new Dod();

        System.out.println(x);
        System.out.println(y);

        System.out.println(x.dod(5, 7));
        System.out.println(y.dod(5, 7));

        IInterface z = new IInterface() {

            @Override
            public void sayHi() {
                System.out.println("Hi");
            }
        };

        z.sayHi();
    }
}
