/**
 * 
 */
package com.patel.pradeep.concurrency.executorCompletion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.patel.pradeep.utility.Threads;

/**
 * @author prade 
 * 
 * 	http://www.nurkiewicz.com/2013/02/executorcompletionservice-in-practice.html
 * 	https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorCompletionService.html
 * 
 */
public class ExecutorCompletionServiceCallableFuture_02_2 {

	final static List<String> topSites = Arrays.asList("www.google.com", "www.youtube.com", "www.yahoo.com",
			"www.msn.com", "www.wikipedia.org", "www.baidu.com", "www.microsoft.com", "www.qq.com", "www.bing.com",
			"www.ask.com", "www.adobe.com", "www.taobao.com", "www.youku.com", "www.soso.com", "www.wordpress.com",
			"www.sohu.com", "www.windows.com", "www.163.com", "www.tudou.com", "www.amazon.com");

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		System.out.println("Site List size->"+topSites.size());
		
		ExecutorCompletionServiceCallableFuture_02_2 ecsc = new ExecutorCompletionServiceCallableFuture_02_2();
		final ExecutorService pool = Executors.newFixedThreadPool(topSites.size());
		List<Callable<String>> contentsFutures = new ArrayList<>(topSites.size());

		for (final String site : topSites) {
			contentsFutures.add(new Task(site));
		}
		ecsc.solve(pool,contentsFutures);	
		pool.shutdown();
	}

	static class Task implements Callable<String>{
		private String site;
		public Task(String site) {
			super();
			this.site = site;
		}
		@Override
		public String call() throws Exception {
			return site + " " + Thread.currentThread().getName() + " "
					+ Threads.sleepRandom(10000);
		}
	}
	
	void solve(Executor executor, Collection<Callable<String>> solvers) throws InterruptedException, ExecutionException {
		CompletionService<String> ecs = new ExecutorCompletionService<String>(executor);
		for (Callable<String> solver : solvers){
			ecs.submit(solver);
		}
		int n = solvers.size();
		for (int i = 0; i < n; ++i) {
			String r = ecs.take().get();
			if (r != null)
				use(r);
		}
	}

	void use(String r){
		System.out.println(r);
	}
}
