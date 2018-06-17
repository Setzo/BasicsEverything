package main;

import org.springframework.stereotype.Component;

@Component( "car" )
public class Car {

    public void type(int x) {
        System.out.println("type");
    }
}
