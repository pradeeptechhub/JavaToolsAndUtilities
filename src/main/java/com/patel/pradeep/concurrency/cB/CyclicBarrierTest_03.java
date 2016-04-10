/**
 * 
 */
package com.patel.pradeep.concurrency.cB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.patel.pradeep.utility.Threads;

/**
 * @author prade
 * http://www.concretepage.com/java/example_cyclicbarrier_java
 */
public class CyclicBarrierTest_03 {
	
	final CyclicBarrier barrierTest;
	List<Integer> list = new ArrayList<>();
	
	List<Callable<Void>> tasks = 
			Arrays.asList(new Task(11, 22), new Task(22, 33),
					new Task(5, 20));

	
	static ExecutorService executor = Executors.newFixedThreadPool(3);
	//3 threads are must as CB wait for 3 thread/task to wait/complete.
	
	CyclicBarrierTest_03() {
		barrierTest = new CyclicBarrier(3, new Runnable() {
			public void run() {
				addListvalue();
			}
		});
		
		executor.submit(new Task(5, 10));
		executor.submit(new Task(20, 30));
		executor.submit(new Task(15, 20));
		
		try {
			executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CyclicBarrierTest_03();
		executor.shutdown();
	}

	void addListvalue() {
		int total = 0;
		for (int j = 0; j < list.size(); j++) {
			total += list.get(j);
		}
		System.out.println("Total addtion:" + total);
	}

	class Task implements Callable<Void> {
		int start, end = 0;

		Task(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public Void call() {
			add(start, end);
			try {
				System.out.println(Thread.currentThread().getName() + " awaiting");
				barrierTest.await();
				return null;
			} catch (InterruptedException ex) {
				return null;
			} catch (BrokenBarrierException ex) {
				return null;
			}
		}
		
		void add(int start, int end) {
			int sum = 0;
			for (int s = start; s <= end; s++) {
				sum += s;
			}
			System.out.println(Thread.currentThread().getName()+" sleep("+Threads.sleepRandom(1000) + ") " + start+"..."+end);
			list.add(sum);
			System.out.println(Thread.currentThread().getName()+" Addition:" + sum);
		}
	}

}
