/**
 * 
 */
package com.patel.pradeep.concurrency.threads;

/**
 * @author prade
 * Java The Complete Reference 9th ed 2014
 */
public class NewThread implements Runnable {
	/*private*/ String name; // name of thread
	/*private*/ Thread t;
	NewThread(String threadname) {
		name = threadname;
		t = new Thread(this, name);
		System.out.println("New thread: " + t);
		t.start(); // Start the thread
	}
	@Override
	public void run() {
		try {
			for (int i = 5; i > 0; i--) {
				System.out.println(name + ": " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println(name + "Interrupted");
		}
		System.out.println(name + " exiting.");
	}
}
