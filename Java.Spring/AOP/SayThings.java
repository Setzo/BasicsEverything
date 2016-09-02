package main;

import org.springframework.stereotype.Component;

@Component( "sayThings" )
public class SayThings {

    public void sayHi() {
        System.out.println("Hi.");
    }

    public void sayHi(String hi) {
        System.out.println(hi);
    }

    public int sayHi(int hi) {
        System.out.println(hi);
        return hi;
    }

    public Object doSth() {
        System.out.println("Something");
        return null;
    }
}
