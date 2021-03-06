package demo10;

import java.util.Scanner;

public class Processor {

    public void produce() throws InterruptedException {
       synchronized (this) {
           System.out.println("Produce thread is runner ...");
           wait();
           System.out.println("Resumed ... ");
       }
    }

    public void consumer() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Waiting to return key .... ");
            scanner.nextLine();
            System.out.println("Returned to key pressed ... ");
            notify();
            Thread.sleep(5000);
        }
    }
}
