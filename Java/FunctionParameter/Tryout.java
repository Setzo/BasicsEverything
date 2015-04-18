import java.util.function.Function;

public class Tryout {

	// Function<X, Y> -- X - argument type, Y - return type  
	public int applyFunction(Function<String, Integer> fnc, String name) {
		
		return fnc.apply(name);
	}
}
