import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class Processor implements Runnable {

	private int id = 0;
	
	public Processor(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		long t = System.currentTimeMillis();
		System.out.println("Process#" + id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(id + "  " + (System.currentTimeMillis()-t));
	}
}

public class ThPool {
	public static void main(String[] args) {
		
		ExecutorService exServ = Executors.newFixedThreadPool(2);
		
		for(int i=0; i<5; i++) {
			exServ.submit(new Processor(i));
		}
		exServ.shutdown();
		System.out.println("All tasks sumbitted");
		try {
			exServ.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All tasks finished");
	}
}
