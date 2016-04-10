/**
 * 
 */
package com.patel.pradeep.concurrency.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author prade
 * Java Programming 2012
 */
public class WorkerThread implements Runnable {

	private Semaphore semaphore;
	private int counter;
	private static int increCounter;

	public WorkerThread(Semaphore semaphore, int counter) {
		super();
		this.semaphore = semaphore;
		this.counter = counter;
	}
	
	public WorkerThread(Semaphore semaphore) {
		super();
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			if(semaphore.tryAcquire(100, TimeUnit.MILLISECONDS)){
				try{
					Teller.getService(counter);  //AAA
					//Teller.getService(increCounter++); //BBB
				}finally{
					semaphore.release();
				}				
			}else{
				System.out.println(Thread.currentThread().getName() + " not attended");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
