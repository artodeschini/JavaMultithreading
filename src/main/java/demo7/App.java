package demo7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This example shows how to control the number of threads to execute simultaneously
 *
 * Use ExecutorServe and fixe a number of Thread
 *
 * note: the method shutdown of ExecutorService makes all Runnable are performed ( <-- execute)
 */
class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting id ... " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End id ... " + id);
    }
}

public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5 ; i++) {
            executor.submit( new Processor( i ) );
        }

        executor.shutdown();

        System.out.println("All task submit ... ");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All task completed ... ");
    }
}
