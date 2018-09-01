package demo3;

/**
 * This sample show how to create Thread with anonymous class r1 and r2 with lambda of Java 8
 */
public class App {

	public static void main(String[] args) {
		Thread r1 = new Thread(new Runnable() {
			
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
		});
		
		//Java 8 for lambda
		Thread r2 = new Thread( () -> {
			for (int i = 0; i < 10; i++) {
				System.out.println("Hello " + i);
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		r1.start();
		r2.start();
	}

}
