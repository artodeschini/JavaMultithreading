package demo13;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private Account acc1 = new Account();
    private Account acc2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock lock1, Lock lock2) throws InterruptedException {
        while (true) {


            boolean gotLock1 = false;
            boolean gotLock2 = false;

            try {

                gotLock1 = lock1.tryLock();
                gotLock2 = lock2.tryLock();

            } finally {

                if ( gotLock1 && gotLock2 ) {
                    return;
                }

                if ( gotLock1 ) {
                    lock1.unlock();
                }

                if ( gotLock2 ) {
                    lock2.unlock();
                }

            }

            Thread.sleep(1);
        }

    }

    public void firstThread() throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            //lock1.lock();
            //lock2.lock();
            acquireLocks(lock1, lock2);

            try {
                Account.transfer(acc1, acc2, random.nextInt( 100 ) );
            } finally {
                lock1.unlock();
                lock2.unlock();
            }

        }

    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            //lock2.lock();
            //lock1.lock();
            acquireLocks(lock2, lock2);

            try {
                Account.transfer(acc2, acc1, random.nextInt( 100 ) );
            } finally {
                lock2.unlock();
                lock1.unlock();
            }

        }

    }

    public void finished() throws InterruptedException {
        System.out.println("Account 1 balance is " + acc1.getBalance() );
        System.out.println("Account 2 balance is " + acc2.getBalance() );
        System.out.println("Account total balance is " + ( acc1.getBalance() + acc2.getBalance() ) );

    }
}
