/**
 * 
 */
package com.patel.pradeep.concurrency.executorCompletion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.patel.pradeep.utility.Threads;

/**
 * @author prade
 * 
 *         http://www.nurkiewicz.com/2013/02/executorcompletionservice-in-
 *         practice.html
 */
public class ExecutorServiceCallableFuture_02 {

	final static List<String> topSites = Arrays.asList("www.google.com", "www.youtube.com", "www.yahoo.com",
			"www.msn.com", "www.wikipedia.org", "www.baidu.com", "www.microsoft.com", "www.qq.com", "www.bing.com",
			"www.ask.com", "www.adobe.com", "www.taobao.com", "www.youku.com", "www.soso.com", "www.wordpress.com",
			"www.sohu.com", "www.windows.com", "www.163.com", "www.tudou.com", "www.amazon.com");

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ExecutorService pool = Executors.newFixedThreadPool(topSites.size());
		List<Future<String>> contentsFutures = new ArrayList<>(topSites.size());

		for (final String site : topSites) {
			final Future<String> contentFuture = pool.submit(new CallableTask(site));
			contentsFutures.add(contentFuture);
		}

		for (Future<String> contentFuture : contentsFutures) {
			final String content = contentFuture.get();
			System.out.println(content);
		}

		pool.shutdown();
	}
	
	static class CallableTask implements Callable<String>{
		private String site;
		public CallableTask(String site) {
			super();
			this.site = site;
		}
		@Override
		public String call() throws Exception {
			return site + " " + Thread.currentThread().getName() + " "
					+ Threads.sleepRandom(10000);
		}
	}
}
