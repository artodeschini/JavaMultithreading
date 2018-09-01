package demo4;

import java.util.Scanner;

class Processor extends Thread {
	
	private volatile boolean running = true;
	
	@Override
	public void run() {
		while (running) {
			System.out.println("Hello");
			
			try {
				Thread.sleep(100l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutDown() {
		running = false;
	}
}

public class App {
	
	public static void main(String[] args) {
		Processor t = new Processor();
		t.start();
		
		System.out.println("Press enter to exit");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		t.shutDown();
		
		scanner.close();
	}

}
