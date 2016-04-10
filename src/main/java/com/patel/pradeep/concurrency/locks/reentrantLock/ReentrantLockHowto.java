/**
 * 
 */
package com.patel.pradeep.concurrency.locks.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

import com.patel.pradeep.utility.Threads;

/**
 * @author prade
 *
 */
public class ReentrantLockHowto {

	private final ReentrantLock lock = new ReentrantLock();
	private int count = 0;

	// Locking using Lock and ReentrantLock
	public int getCount() {
		System.out.println(Thread.currentThread().getName() + " waiting...");
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " gets Count: " + count);
			return count++;
		} finally {
			lock.unlock();
		}
	}

	// Implicit locking using synchronized keyword
	public synchronized int getCountTwo() {
		return count++;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final ReentrantLockHowto counter = new ReentrantLockHowto();
		
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while (counter.getCount() < 6) {
					Threads.sleepRandom(1000, "");
				}
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				while (counter.getCount() < 6) {
					Threads.sleepRandom(1000, "");
				}
			}
		};

		t1.start();
		t2.start();
	}
}
