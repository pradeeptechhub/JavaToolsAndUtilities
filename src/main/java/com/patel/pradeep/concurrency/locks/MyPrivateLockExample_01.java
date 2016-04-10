/**
 * 
 */
package com.patel.pradeep.concurrency.locks;
/**
 * @author prade
 * http://www.javacodegeeks.com/2014/09/java-concurrency-tutorial-locking-intrinsic-locks.html
 */
public class MyPrivateLockExample_01 extends PrivateLockExample {
	public synchronized void executeAnotherTask() throws InterruptedException {
		System.out.println("executeAnotherTask - Entering...");
		Thread.sleep(300);
		System.out.println("executeAnotherTask - Exiting...");
	}
	public static void main(String[] args) {
		MyPrivateLockExample_01 privateLock = new MyPrivateLockExample_01();

		Thread t1 = new Thread(new Worker1(privateLock));
		Thread t2 = new Thread(new Worker2(privateLock));
		t1.start();
		t2.start();
	}

	private static class Worker1 implements Runnable {
		private final MyPrivateLockExample_01 privateLock;
		public Worker1(MyPrivateLockExample_01 privateLock) {
			this.privateLock = privateLock;
		}
		@Override
		public void run() {
			try {
				privateLock.executeTask();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class Worker2 implements Runnable {
		private final MyPrivateLockExample_01 privateLock;
		public Worker2(MyPrivateLockExample_01 privateLock) {
			this.privateLock = privateLock;
		}
		@Override
		public void run() {
			try {
				privateLock.executeAnotherTask();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class PrivateLockExample {
	//private Object myLock = new Object();
	public Object myLock = new Object();
	public void executeTask() throws InterruptedException {
		synchronized (myLock) {
			System.out.println("executeTask - Entering...");
			Thread.sleep(300);
			System.out.println("executeTask - Exiting...");
		}
	}
}
/*
executeTask - Entering...
executeAnotherTask - Entering...
executeAnotherTask - Exiting...
executeTask - Exiting...
*/