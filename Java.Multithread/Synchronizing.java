
public class App {

    private int cnt = 0;

    public synchronized void incr() {
        System.out.println(cnt);
        cnt++;
    }

    public static void main(String[] args) {

        long time = System.currentTimeMillis();
        App app = new App();
        app.doWork();
        System.out.println(System.currentTimeMillis() - time + " ms");

    }

    public void doWork() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    incr();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    incr();
                }
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
    }
}
