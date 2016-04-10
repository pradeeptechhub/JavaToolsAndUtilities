/**
 * 
 */
package com.patel.pradeep.concurrency.cDL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author prade http://farenda.com/java/java-countdownlatch-example/
 * 
 *         The latch is created with some counter value, tasks decrease
 *         counter’s value down to zero, which signals some other thread that
 *         jobs have finished.
 */
public class CountDownLatchExample_01 {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CountDownLatch latch = new CountDownLatch(4);
		ExecutorService executor = Executors.newCachedThreadPool();

		String message = "Hello, World";
		List<Callable<String>> tasks = Arrays.asList(new Lowerer(latch, message, new Random().nextInt(1000)),
				new Upperer(latch, message, new Random().nextInt(1000)), new Lowerer(latch, message, new Random().nextInt(1000)),
				new Upperer(latch, message, new Random().nextInt(1000)));

		System.out.println("Converting message to lowercase and uppercase:");
		List<Future<String>> results = new ArrayList<>();
		for (Callable<String> task : tasks) {
			results.add(executor.submit(task));
		}

		// wait for tasks to finish:
		latch.await();

		System.out.println("Shutting down the executor.");
		executor.shutdown();

		System.out.println(results.get(1).get());
		
		System.out.println("Getting converted results:");
		for (Future<String> result : results) {
			System.out.printf("Converted message: %s%n", result.get());
		}

	}

	private static abstract class ConverterTask implements Callable<String> {

		private final CountDownLatch latch;
		private String value;
		private long sleep;

		public ConverterTask(CountDownLatch latch, String value, long sleep) {
			this.latch = latch;
			this.value = value;
			this.sleep = sleep;
		}

		@Override
		public String call() throws Exception {
			value = convert(value, sleep);
			System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getState());
			latch.countDown();
			return value +" "+ Thread.currentThread().getName();
		}

		protected abstract String convert(String value, long sleep);
	}

	private static class Lowerer extends ConverterTask {

		public Lowerer(CountDownLatch latch, String value, long sleep) {
			super(latch, value, sleep);
		}

		@Override
		public String convert(String value, long sleep) {
			try {
				System.out.println(Thread.currentThread().getName() + ":Lowerer sleeping for " + sleep);
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return value.toLowerCase();
		}
	}

	private static class Upperer extends ConverterTask {

		public Upperer(CountDownLatch latch, String value, long sleep) {
			super(latch, value, sleep);
		}

		@Override
		public String convert(String value, long sleep) {
			try {
				System.out.println(Thread.currentThread().getName() + ":Upperer sleeping for " + sleep);
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return value.toUpperCase();
		}
	}

}
