package rerntrantLock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void incr() {
        for (int i = 0; i < 1E4; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting for lock");
        cond.await();
        System.out.println("Got the lock, running");
        try {
            incr();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings( "resource" )
    public void secondThread() throws InterruptedException {
        Thread.sleep(100);
        lock.lock();
        System.out.println("Press key");
        new Scanner(System.in).nextLine();
        System.out.println("Released the lock");
        cond.signal();
        try {
            incr();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println(count);
    }
}
