package main;

public class App {

    public static void main(String[] args) {

        Impl checkImplementation = new Impl();

        System.out.println(checkImplementation.eqls(5, 5));

        System.out.println(checkImplementation.eqls(2, 5));

        System.out.println(checkImplementation.eqls(5, 2));

    }

}
