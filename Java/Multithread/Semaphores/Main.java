package semaph;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Semaphores {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService exServ = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            exServ.submit(new Runnable() {
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        exServ.shutdown();
        exServ.awaitTermination(1, TimeUnit.HOURS);
        /*Semaphore sem = new Semaphore(1, true);
		sem.release();						//Permits++
		sem.acquire();						//Permits-- //Throws IE
		System.out.println(sem.availablePermits());
		*/
    }
}
