package demo6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this sample show how to use this sample show how to use the reserved word 'synchronized' in code block
 *
 * note: I use a Object to lock de execute, this improve the speed to execute
 *
 */
public class Work {
	
	Random random = new Random();

	Object lock1 = new Object();
	Object lock2 = new Object();

	List<Integer> numbers1 = new ArrayList<>();
	List<Integer> numbers2 = new ArrayList<>();
	
	public void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			numbers1.add( random.nextInt(100 ) );
		}

	}
	
	public void stageTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			numbers2.add( random.nextInt(100) );
		}
	}
	
	public void process() {
		for (int i = 0; i < 1000; i++) {
			this.stageOne();
			this.stageTwo();
		}
	}
	
	public void main() {
		System.out.println("Starting ... ");
		
		long start = System.currentTimeMillis();

		Thread t1 = new Thread(() -> {
			process();
		});

		Thread t2 = new Thread(() -> {
			process();
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		long end = System.currentTimeMillis();
		
		System.out.println("Time takes is: " + ( end - start ) );

		System.out.println("List1 " + numbers1.size() + " ; List2 " + numbers2.size() );
	}

}
