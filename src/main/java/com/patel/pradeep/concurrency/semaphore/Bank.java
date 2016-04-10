/**
 * 
 */
package com.patel.pradeep.concurrency.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author prade
 * Java Programming 2012 page (486) Synchronizers (Semaphores)
 */
public class Bank {
	private static int COUNT = 10;
	private static Semaphore semaphore = new Semaphore(2, true);

	public static void main(String[] args) {
		Thread[] threads = new Thread[COUNT];
		WorkerThread workerThread = new WorkerThread(semaphore);
		for(int i=0;i<COUNT;i++){
			threads[i] = new Thread(new WorkerThread(semaphore,i)); //AAA
			//threads[i] = new Thread(workerThread); //BBB
			threads[i].start();
		}
	}
}
