package demo14;

import java.util.concurrent.Semaphore;

public class Connection {

    public static Connection instance = new Connection();

    private Semaphore sem = new Semaphore(10, true);

    private int connections = 0;

    private Connection() {

    }

    public static Connection getInstance() {
        return instance;
    }

    public void connection() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnection();
        } finally {
            sem.release();
        }
    }

    public void doConnection() {

        synchronized ( this ) {
            connections++;
            System.out.println("Currents connections: " +  connections );
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized ( this ) {
            connections--;
        }
    }
}
