package main;

import org.springframework.stereotype.Component;

@Component("logger")
public class Logger {

	public void aboutToSayHi() {
		System.out.println("About to say Hi.");
	}
	
	public void saidHi() {
		System.out.println("Said hi.");
	}
}
