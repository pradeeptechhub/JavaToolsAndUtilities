/**
 * 
 */
package com.patel.pradeep.concurrency.executorCompletion;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.patel.pradeep.utility.Threads;

/**
 * @author prade 
 * 
 * 	http://www.nurkiewicz.com/2013/02/executorcompletionservice-in-practice.html
 * 
 */
public class ExecutorCompletionServiceCallableFuture_02 {

	final static List<String> topSites = Arrays.asList("www.google.com", "www.youtube.com", "www.yahoo.com",
			"www.msn.com", "www.wikipedia.org", "www.baidu.com", "www.microsoft.com", "www.qq.com", "www.bing.com",
			"www.ask.com", "www.adobe.com", "www.taobao.com", "www.youku.com", "www.soso.com", "www.wordpress.com",
			"www.sohu.com", "www.windows.com", "www.163.com", "www.tudou.com", "www.amazon.com");

	public static void main(String[] args) {
		
		System.out.println("Site List size->"+topSites.size()+"\n");
		
		final ExecutorService executorPool = Executors.newFixedThreadPool(topSites.size());
		CompletionService<String> ecs = new ExecutorCompletionService<String>(executorPool);
		
		for (final String site : topSites) {
			ecs.submit(new CallableTask(site));
		}
		System.out.println("Task Submitted...");

		new Thread(new Runnable(){
			@Override
			public void run() {
				for (int i=0;i<topSites.size();i++) {
					try {
						final Future<String> future = ecs.take();
				        final String content = future.get();
				        System.out.println(content);
				    } catch (ExecutionException | InterruptedException e) {
				        System.out.println("Error while downloading:\n"+ e.getCause());
				    }
				}				
			}
		}).start();
		
		System.out.println("\nMain Completed...");
		executorPool.shutdown();
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
