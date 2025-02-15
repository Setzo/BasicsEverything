package package2;

class Runner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hello: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
}

public class Multithread2 {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());

        t1.start();
        t2.start();
    }
}
