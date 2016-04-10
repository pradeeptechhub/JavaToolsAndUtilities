/**
 * 
 */
package com.patel.pradeep.concurrency.cB;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author prade
 *
 *         http://www.jamesward.com/2011/09/21/java-concurrency-with-akka-
 *         composing-futures
 */
public class CyclicBarrier_01_VIMP {

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException, ExecutionException {
		
		List<Future<Long>> futures = new ArrayList<>();
		ExecutorService executorService = Executors.newFixedThreadPool(4);

		// 3 means we submit 2 tasks, that's the disadvantage of using
		// CyclicBarrier
		CyclicBarrier barrier = new CyclicBarrier(3);
		futures.add(executorService.submit(new RandomPause(barrier)));
		futures.add(executorService.submit(new RandomPause(barrier)));

		barrier.await();
		System.out.println("All RandomPause's are complete");

		long totalPause = 0L;
		for (Future<Long> future : futures) {
			System.out.println("One pause was for " + future.get() + " ms");
			totalPause += future.get();
		}
		System.out.println("Total pause was for " + totalPause + " ms");
		executorService.shutdown();
	}
}

class RandomPause implements Callable<Long> {
	private Long millisPause;
	private CyclicBarrier barrier;
	
	public RandomPause(CyclicBarrier barrier) {
		this.barrier = barrier;
		millisPause = Math.round(Math.random() * 8000) + 200;
	}

	public Long call() throws Exception {
		Thread.sleep(millisPause);
		System.out.println(Thread.currentThread().getName() + " was paused for " + millisPause + " ms");
		barrier.await();
		return millisPause;
	}
}