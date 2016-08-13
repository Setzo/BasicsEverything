package main;

import org.springframework.stereotype.Component;

@Component("sayThings")
public class SayThings implements TestI {
	
	public SayThings() {
		System.out.println("Constructor");
	}
	
	public void sayHi() throws Exception {
		System.out.println("Hi.");
		
		throw new Exception();
	}

	@Override
	public void x() {
		System.out.println("x");
	}
}
