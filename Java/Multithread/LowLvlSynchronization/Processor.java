package lowLvlSynchro;

import java.util.LinkedList;
import java.util.Random;

public class Processor {

    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException {

        int val = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(val++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {

        Random rng = new Random();
        while (true) {
            synchronized (lock) {

                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.print(list.size());
                int val = list.removeFirst();
                System.out.println(" " + val);
                lock.notify();
            }
            Thread.sleep(rng.nextInt(1000));
        }
    }
}
