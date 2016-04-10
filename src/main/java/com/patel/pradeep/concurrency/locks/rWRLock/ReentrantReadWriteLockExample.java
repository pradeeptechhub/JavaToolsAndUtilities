/**
 * 
 */
package com.patel.pradeep.concurrency.locks.rWRLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author prade
 * http://examples.javacodegeeks.com/core-java/util/concurrent/locks-concurrent/reentrantlock/java-reentrantreadwritelock-example/
 */
public class ReentrantReadWriteLockExample {

	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
	private static String message = "a";

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Read());
		Thread t2 = new Thread(new WriteA());
		Thread t3 = new Thread(new WriteB());
		Thread t4 = new Thread(new Read1());
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
	}

	static class Read implements Runnable {

		public void run() {
			for (int i = 0; i <= 10; i++) {
				if (lock.isWriteLocked()) {
					System.out.println("I'll take the lock from Write");
				}
				lock.readLock().lock();
				System.out.println("ReadThread " + Thread.currentThread().getId() + " ---> Message is " + message);
				lock.readLock().unlock();
			}
		}
	}
	
	static class Read1 implements Runnable {

		public void run() {
			for (int i = 0; i <= 10; i++) {
				if (lock.isWriteLocked()) {
					System.out.println("I'll take the lock from Write");
				}
				lock.readLock().lock();
				System.out.println("ReadThread1 " + Thread.currentThread().getId() + " ---> Message is " + message);
				lock.readLock().unlock();
			}
		}
	}

	static class WriteA implements Runnable {

		public void run() {
			for (int i = 0; i <= 10; i++) {
				try {
					lock.writeLock().lock();
					message = message.concat("a");
				} finally {
					lock.writeLock().unlock();
				}
			}
		}
	}

	static class WriteB implements Runnable {

		public void run() {
			for (int i = 0; i <= 10; i++) {
				try {
					lock.writeLock().lock();
					message = message.concat("b");
				} finally {
					lock.writeLock().unlock();
				}
			}
		}
	}
}
