package deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private Account acc1 = new Account();
    private Account acc2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock l1, Lock l2) throws InterruptedException {
        while (true) {

            boolean gotL1 = false;
            boolean gotL2 = false;
            try {
                gotL1 = l1.tryLock();
                gotL2 = l2.tryLock();
            } finally {
                if (gotL1 && gotL2) {
                    return;
                }
                if (gotL1) {
                    l1.unlock();
                }
                if (gotL2) {
                    l2.unlock();
                }
            }
            Thread.sleep(1);
        }
    }

    public void firstThread() throws InterruptedException {

        Random rng = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);

            try {
                Account.transfer(acc1, acc2, rng.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {

        Random rng = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock2, lock1);

            try {
                Account.transfer(acc2, acc1, rng.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println(acc1.getBalance() + " " + acc2.getBalance() + " " + (acc1.getBalance() + acc2.getBalance()));
    }
}
