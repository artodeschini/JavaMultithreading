package demo12;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author Artur
 * in this sample show to re enter a lock processs
 */
public class Runner {

    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();

        System.out.println("Waiting ... ");
        condition.await();

        System.out.println("Wake up!");

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000 );
        lock.lock();

        System.out.println("Press to return key!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Return to key");
        condition.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() throws InterruptedException {
        System.out.println("count is " + count);
    }
}
