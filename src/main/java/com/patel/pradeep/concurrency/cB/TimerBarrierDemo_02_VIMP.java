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
public class TimerBarrierDemo_02_VIMP {

	public static void main(String[] args) {
		int threads = 3;
		final CyclicBarrier barrier = new CyclicBarrier(threads, new BarrierTimer());

		ExecutorService svc = Executors.newFixedThreadPool(threads);
		for (int i = 0; i < threads; i++) {
			svc.execute(new Runnable() {
				public void run() {
					try {
						barrier.await();
						long sleepTime = (int) (Math.random() * 1000);
						System.out.println(Thread.currentThread().getId() + " working for " + sleepTime);
						Thread.sleep(sleepTime);
						barrier.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		svc.shutdown();
	}

	private static class BarrierTimer implements Runnable {
		private volatile long start;

		public void run() {
			System.out.println("START--->" + start);
			if (start == 0) {
				start = System.currentTimeMillis();
			} else {
				long end = System.currentTimeMillis();
				long elapsed = (end - start);
				System.out.println("Completed in " + elapsed + " ms");
			}
		}
	}
}
