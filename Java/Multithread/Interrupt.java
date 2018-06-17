package interruptThreads;

import java.util.Random;

public class InterruptThreads {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting");
        Thread t1 = new Thread(new Runnable() {

            public void run() {
                Random rng = new Random();

                for (int i = 0; i < 1E8; i++) {

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted");
                        break;
                    }
                    Math.sin(rng.nextDouble());
                }
            }
        });

        t1.start();
        Thread.sleep(500);
        t1.interrupt();

        t1.join();
        System.out.println("Finished");
    }
}
