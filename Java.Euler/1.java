package euler1;

import java.util.stream.IntStream;

public class Euler1 {

	public static void main(String[] args) {
		
		System.out.print(IntStream.range(0, 1000).filter(x -> x % 3 == 0 || x % 5 == 0).sum());
	}

}
