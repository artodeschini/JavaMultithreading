package demo11;

import java.util.LinkedList;
import java.util.Random;

public class Processor {

    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    /**
     * this sample use low level synchronization
     *
     * @throws InterruptedException
     */
    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {
                while ( list.size() == LIMIT ) {
                    lock.wait(); //1
                }
                list.add( value++ );
                lock.notify(); //2
            }
        }

    }

    public void consumer() throws InterruptedException {

        Random random = new Random();

        while (true) {
            synchronized (lock) {
                while ( list.size() == 0 ) {
                    lock.wait(); //2
                }

                System.out.println("List size : " + list.size() );
                int value = list.removeFirst();
                System.out.println("; value is " + value);
                lock.notify(); //1
            }
            Thread.sleep( random.nextInt( 1000 ) );
        }
    }

}
