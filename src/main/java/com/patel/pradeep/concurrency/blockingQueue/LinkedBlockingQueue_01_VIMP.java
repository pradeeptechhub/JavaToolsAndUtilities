/**
 * 
 */
package com.patel.pradeep.concurrency.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author prade
 * 
 * Java Programming 2012 Page 480 (bit modified for simplicity).
 */
public class LinkedBlockingQueue_01_VIMP {
	
	public static int THREAD_COUNT=4;
	public static int PRODUCT_COUNT=15;
	//Total Products = (No. of Threads) X (No. of Products per Thread)
	
	public static void main(String[] args) {
		
		BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
		Thread[] producerThreads = new Thread[THREAD_COUNT];
		Thread[] consumerThreads = new Thread[THREAD_COUNT];
		
		Producer producer = new Producer(blockingQueue,PRODUCT_COUNT);
		Consumer consumer = new Consumer(blockingQueue,PRODUCT_COUNT);
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			producerThreads[i] = new Thread(producer);
			consumerThreads[i] = new Thread(consumer);
			producerThreads[i].start();
			consumerThreads[i].start();
		}
	}

	static class Producer implements Runnable {
		private BlockingQueue<Integer> blockingQueue;
		private int productCount;
		private static Integer count = new Integer(1);

		public Producer(BlockingQueue<Integer> blockingQueue, int productCount) {
			this.blockingQueue = blockingQueue;
			this.productCount = productCount;
		}
		@Override
		public void run() {
			for(int i=0;i<productCount;i++){
				try {
					blockingQueue.put(count++);
					System.out.println(Thread.currentThread().getName() + ":PUT:" +(count-1));
				} catch (InterruptedException e) {	}
			}
		}
	}

	static class Consumer implements Runnable {
		private BlockingQueue<Integer> blockingQueue;
		private int productCount;
		public Consumer(BlockingQueue<Integer> blockingQueue, int productCount) {
			this.blockingQueue = blockingQueue;
			this.productCount = productCount;
		}
		@Override
		public void run() {
			for(int i=0;i<productCount;i++){
				try {
					System.out.println(Thread.currentThread().getName()+":TAKE:" + blockingQueue.take());
				} catch (InterruptedException e) {	}
			}
		}
	}

}
