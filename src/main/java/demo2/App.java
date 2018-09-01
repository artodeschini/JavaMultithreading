package demo2;

class Runner implements Runnable {

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
		Thread r1 = new Thread(new Runner());
		Thread r2 = new Thread(new Runner());
		
		r1.start();
		r2.start();
	}

}
