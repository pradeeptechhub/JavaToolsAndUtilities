/**
 * 
 */
package com.patel.pradeep.concurrency.cB;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author prade
 *
 * http://k2java.blogspot.in/2011/06/cyclicbarrier.html
 */
public class CyclicBarrierDemo_02_VIMP {

	public static void main(String[] args) throws InterruptedException {
		int nThreads = 3;
		final CyclicBarrier barrier = new CyclicBarrier(nThreads);
		ExecutorService esvc = Executors.newFixedThreadPool(nThreads);
		
		for (int i = 0; i < nThreads; i++) {
			esvc.execute(new Runnable() {
				public void run() {
					try {
						log("At run()");
						barrier.await();
						System.out.println("ppppp");
						log("Do work");
						Thread.sleep((int) (Math.random() * 1000));

						log("Wait for end");
						barrier.await();

						log("Done");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			Thread.sleep(100);
		}
		esvc.shutdown();
	}

	private static void log(String msg) {
		System.out.println(System.currentTimeMillis() + ": " + Thread.currentThread().getId() + " " + msg);
	}
}
