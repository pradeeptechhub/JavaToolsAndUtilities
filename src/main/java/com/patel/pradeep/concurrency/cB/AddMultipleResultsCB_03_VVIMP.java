/**
 * 
 */
package com.patel.pradeep.concurrency.cB;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author prade
 *
 */
public class AddMultipleResultsCB_03_VVIMP {
	static BlockingQueue<Long> results = new ArrayBlockingQueue<>(2);

	public static void main(String[] args) {
		int LIST_SIZE = 2;
		final CyclicBarrier barrier = new CyclicBarrier(LIST_SIZE, new BarrierTimer());

		ExecutorService es = Executors.newFixedThreadPool(LIST_SIZE);

		List<Long> list = new ArrayList<Long>();
		// Fill the ArrayList with Sample Data.
		for (long i = 0; i < 50; i++) {
			list.add(i + 1);
		}
		es.execute(new Results(barrier, list.subList(0, 20)));
		es.execute(new Results(barrier, list.subList(20, list.size())));

		long expected = 0;
		for (int i = 0; i < list.size(); i++) {
			expected += list.get(i);
		}
		System.out.println("expected->" + expected);
		es.shutdown();
	}

	private static class BarrierTimer implements Runnable {
		public void run() {
			System.out.println("PPPPPPPP");
			long total = 0;
			for (Long f : results) {
				System.out.println("f->" + f);
				total += f;
			}
			System.out.println("Actual->" + total);
		}
	}

	static class Results implements Runnable {
		private List<Long> list;
		private CyclicBarrier barrier;

		public Results(CyclicBarrier barrier, List<Long> list) {
			this.barrier = barrier;
			this.list = list;
		}

		@Override
		public void run() {
			try {
				long tmp = 0;
				for (int i = 0; i < list.size(); i++) {
					tmp += list.get(i);
				}
				results.add(tmp);
				System.out.println("BROKEN");
				barrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
