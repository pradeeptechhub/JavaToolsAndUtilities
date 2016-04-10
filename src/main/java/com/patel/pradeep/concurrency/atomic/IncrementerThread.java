/**
 * 
 */
package com.patel.pradeep.concurrency.atomic;

/**
 * @author prade
 *
 */
public class IncrementerThread extends Thread {
	
	private Counter counter;
	private AtomicCounter atomicCounter;

	public IncrementerThread(Counter counter, AtomicCounter atomicCounter) {
		super();
		this.counter = counter;
		this.atomicCounter = atomicCounter;
	}

	public void run() {
		for (int i = 0; i < 1000000; i++) {
			counter.increment();
			atomicCounter.increment();
		}
	}
}
