import java.util.Scanner;

class CPU extends Thread {
	
	private volatile boolean running=true;
	
	@Override
	public void run() {
		while(running) {
			System.out.println("Hello");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		running=false;
	}
}

public class App {
	public static void main(String[] args) {

		CPU c1 = new CPU();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Press enter to stop..");
		c1.start();
		sc.nextLine();
		c1.shutdown();
		sc.close();
	}
}
