/**
 * 
 */
package com.patel.pradeep.concurrency.cDL;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * @author prade
 *
 */
public class AddMultipleResultsCDL_03_VVIMP {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int THREADS = 2;
		int LIST_SIZE = 100;
		CountDownLatch countDownLatch = new CountDownLatch(THREADS);
		ExecutorService es = Executors.newFixedThreadPool(5);
		List<Long> list = new ArrayList<Long>();
		//Fill the ArrayList with Sample Data.
		for(long i=0;i<LIST_SIZE;i++){
			list.add(i+1);
		}
		
		List<Future<Long>> futures = new ArrayList<>();
		futures.add(es.submit(new Results(countDownLatch,list.subList(0, 20))));
		futures.add(es.submit(new Results(countDownLatch,list.subList(20, list.size()))));
		//Future<Long> f = es.submit(new Results(countDownLatch,list.subList(0, 20)));
		//Future<Long> f1 = es.submit(new Results(countDownLatch,list.subList(20, list.size())));
		//After submit await for the event.
		countDownLatch.await();
		
		//System.out.println("Actual->"+(f.get()+f1.get()));
		long total = 0;
		for(Future<Long> f: futures){
			total+=f.get();
		}
		System.out.println("Actual->"+total);
		long expected = 0;
		for(int i=0;i<list.size();i++){
			expected +=list.get(i);
		}
		System.out.println("expected->"+expected);
		es.shutdown();
	}
	
	static class Results implements Callable<Long> {
		
		private List<Long> list;
		private CountDownLatch countDownLatch;
		
		public Results(CountDownLatch countDownLatch, List<Long> list) {
			this.countDownLatch = countDownLatch;
			this.list = list;
		}

		public Long call() throws Exception {
			long tmp = 0;
			for(int i=0;i<list.size();i++){
				tmp +=list.get(i);
			}
			countDownLatch.countDown();
			return tmp;
		}
	}

}
