package packa1;

import packa1.packa3.Hello;
import packa2.Pack2;

public class Pack1 {
    public static void main(String[] args) {

        Hello var1 = new Hello();                    //packa1.packa3 public class Hello - odwołanie do klasy Hello, tworzenie nowe

        var1.sayHello();                            //wywołanie metody sayHello z klasy Hello

        Pack2 var2 = new Pack2();                    //packa2 public class Pack2
        //nie sa się wywoływać metody welcome() z klasy Wel (packa2) class Wel
        var2.sayWelcome2();                            //wywoływanie metody sayWelcome2() z klasy Pack2
    }
}
