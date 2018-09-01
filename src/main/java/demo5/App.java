package demo5;

/**
 * this sample show how to use reserved word 'synchronized' for signature methods
 * when I use this one Thread enter in the method to execute
 */
public class App {
	
	private volatile int count = 0;

	public static void main(String[] args) {
		App myApp = new App();
		myApp.doWork();
	}
	
	public synchronized void increment() {
		count++;
	}

	public void doWork() {
		//java 8 lambda Runneble implements
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				//count++;
				increment();
			}
		});
		
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				//count++;
				increment();
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
		
		
		System.out.println("count is " + count);
	}

}
