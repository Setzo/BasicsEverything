package main;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component("rng")
public class Rng {

	private static String[] t = {"one", "two", "three", "four", null, null};
	
	public String getT() {
		
		return t[new Random().nextInt(t.length)];
	}
}
