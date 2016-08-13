
interface Executable {
	void execute();
}

interface Executable2 {
	int execute();
}

class Runner {
	public void run(Executable e) {
		System.out.println("Executing code block...");
		e.execute();
	}
}

class Runner2 {
	public void run(Executable2 e) {
		System.out.println("Executing code block2...");
		int val = e.execute();
		System.out.println("Value: " + val);
	}
}


public class LamExpression {

	public static void main(String[] args) {

		Runner runner = new Runner();
		
		runner.run(new Executable() {
			
			public void execute() {
				System.out.println("Hello");
			}
		});
		
		System.out.println();
		System.out.println("..................................");
		System.out.println();
		
		runner.run(() -> System.out.println("Lambda"));
		
		System.out.println();
		System.out.println("..................................");
		System.out.println();
		
		runner.run(() -> {
			
			System.out.println("Hello there");
			System.out.println("Multi lambda");
		});
		
		System.out.println();
		System.out.println("..................................");
		System.out.println();
		
		Runner2 runner2 = new Runner2();
		
		runner2.run(new Executable2() {
			
			public int execute() {
				System.out.println("Returning 7");
				return 7;
			}
		});
		
		System.out.println();
		System.out.println("..................................");
		System.out.println();
		
		runner2.run(() -> {
			
			System.out.println("Returning 9");
			System.out.println("Multi lambda");
			return 9;
		});
		
		System.out.println();
		System.out.println("..................................");
		System.out.println();
		
		runner2.run(() -> 11);
		
	}
}
