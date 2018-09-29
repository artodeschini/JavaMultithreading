package demo13;

public class App {

    public static void main(String[] args) {
        final Runner runner = new Runner();

        Thread t1 = new Thread( () -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread t2 = new Thread( () -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();

            runner.finished();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
