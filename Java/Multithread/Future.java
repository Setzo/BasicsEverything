package calableFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFuture {

    public static void main(String[] args) {
        ExecutorService exServ = Executors.newCachedThreadPool();

        Future<Integer> fut = exServ.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Random rng = new Random();
                int duration = rng.nextInt(4000);
                System.out.println("Start");

                if (duration > 2000) {
                    throw new IOException("2much sleep");
                }

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finished");
                return duration;
            }

        });

        exServ.shutdown();
        try {
            System.out.println(fut.get() + " " + fut.isDone());        //get() czeka na koniec procesu, więc isDone = true

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
            IOException ex = (IOException) e.getCause();
            System.out.println(ex.getMessage());
            e.printStackTrace();
        }
    }
}
