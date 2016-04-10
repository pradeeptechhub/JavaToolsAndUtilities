/**
 * 
 */
package com.patel.pradeep.concurrency.threads;

/**
 * @author prade
 * Java The Complete Reference 9th ed 2014
 */
public class MultiThreadDemo {
	public static void main(String[] args) {
		new NewThread("One"); // start threads
		new NewThread("Two");
		new NewThread("Three");
		/*try {
			// wait for other threads to end
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}*/
		System.out.println("Main thread exiting.");
	}
}
