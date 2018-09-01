package demo9;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) {
        App app = new App();

        Thread t1 = new Thread( () ->{
            try {
                app.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread( () ->{
            try {
                app.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    private void produce() throws InterruptedException {
        Random random = new Random();

        while (true) {
            queue.put( random.nextInt( 100 ) );
        }
    }

    private void consumer() throws InterruptedException {
        Random random = new Random();

        while (true) {
            Thread.sleep(100);

            if ( random.nextInt(10) == 0 ) {
                Integer value = queue.take();

                System.out.println("Taken value: " + value + " ;Queue size is: " + queue.size() );
            }
        }
    }

}
