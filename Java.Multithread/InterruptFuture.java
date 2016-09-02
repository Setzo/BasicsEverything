package interruptThreads;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InterruptThreads {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting");
        ExecutorService exServ = Executors.newCachedThreadPool();

        Future<?> fut = exServ.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {

                Random rng = new Random();
                for (int i = 0; i < 1E8; i++) {

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted");
                        break;
                    }
                    Math.sin(rng.nextDouble());
                }
                return null;
            }

        });

        exServ.shutdown();
        Thread.sleep(500);
        //exServ.shutdownNow();
        fut.cancel(true);
        exServ.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Finished");
    }
}
