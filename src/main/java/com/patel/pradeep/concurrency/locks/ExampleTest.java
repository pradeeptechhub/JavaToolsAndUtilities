/**
 * 
 */
package com.patel.pradeep.concurrency.locks;

import com.patel.pradeep.utility.Threads;

/**
 * @author prade 
 * http://tutorials.jenkov.com/java-concurrency/synchronized.html
 */
public class ExampleTest {
	public static void main(String[] args) {
		Counter counter = new Counter();
		Thread threadA = new CounterThread(counter);
		Thread threadB = new CounterThread(counter);

		threadA.start();
		threadB.start();
		int total = 0;
		for (int i = 0; i <= 10; i++){
			total+=i;
		}
		for (int i = 0; i <= 10; i++){
			total+=i;
		}
		System.out.println("total->"+total);
	}
}

class Counter {
	long count = 0;

	public synchronized void add1(long value) {
		long temp = 1;
		Threads.sleepRandom(200, "");
		this.count = this.count + temp;
		System.out.println(Thread.currentThread().getName() + " " +this.count);
	}
	
	public void add2(long value) {
		synchronized(this){
			long temp = 1;
			Threads.sleepRandom(200, "");
			this.count = this.count + temp;
			System.out.println(Thread.currentThread().getName() + " " +this.count);
		}
	}
	
	public void add(long value) {
		Threads.sleepRandom(200, "");
		long temp = value;
		Threads.sleepRandom(200, "");
		this.count = this.count + temp;
		System.out.println(Thread.currentThread().getName() + " " +this.count);
	}
}

class CounterThread extends Thread {
	protected Counter counter = null;
	protected Object object = new Object();
	public CounterThread(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		for (int i = 0; i <= 10; i++) {
			Threads.sleepRandom(100, "");
			counter.add(i);
			/*synchronized (counter) {
				counter.add(i);
			}*/
			/*synchronized (this) {
				counter.add(i);
			}*/
			/*synchronized (object) {
				counter.add(i);
			}*/
		}
	}
}