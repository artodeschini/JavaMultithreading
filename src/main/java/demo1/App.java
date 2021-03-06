package demo1;

/**
 * This sample show how to create and execute Thread instance when I extends java.lang.Thread
 */
class Runner extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello " + i);
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}


public class App {

	public static void main(String[] args) {
		Runner r1 = new Runner();
		Runner r2 = new Runner();
		
		r1.start();
		r2.start();
	}

}
