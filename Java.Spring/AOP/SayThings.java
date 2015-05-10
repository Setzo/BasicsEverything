package main;

import org.springframework.stereotype.Component;

@Component("sayThings")
public class SayThings {

	public void sayHi() {
		System.out.println("Hi.");
	}
}
