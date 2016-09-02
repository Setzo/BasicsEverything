package package1;

class Runner extends Thread {

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

public class Multithread {
    public static void main(String[] args) {

        Runner r1 = new Runner();
        r1.start();

        Runner r2 = new Runner();
        r2.start();

    }
}
