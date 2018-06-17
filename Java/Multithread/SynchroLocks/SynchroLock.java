import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Work {

    private Random r = new Random();

    private Object l1 = new Object();
    private Object l2 = new Object();

    private List<Integer> arrayL1 = new ArrayList<Integer>();
    private List<Integer> arrayL2 = new ArrayList<Integer>();

    public void s1() {

        synchronized (l1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arrayL1.add(r.nextInt(100));
        }
    }

    public void s2() {

        synchronized (l2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arrayL2.add(r.nextInt(100));
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            s1();
            s2();
        }
    }

    public void main() {
        System.out.println("Starting");
        long ti1 = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // process();
        long ti2 = System.currentTimeMillis();
        System.out.println("Time taken: " + (ti2 - ti1));
        System.out.println("List1: " + arrayL1.size() + " List 2: " + arrayL2.size());
    }
}
