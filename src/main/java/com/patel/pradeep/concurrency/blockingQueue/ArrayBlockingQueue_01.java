/**
 * 
 */
package com.patel.pradeep.concurrency.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author prade http://www.ibm.com/developerworks/library/j-5things4/
 * 
 *         This implements FIFO ordering. This queue is of the fixed size, where
 *         the size is decided at the time of its construction.
 *         
 *         The array capacity cannot be increased at a later time.
 */
public class ArrayBlockingQueue_01 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BlockingQueue<String> drop = new ArrayBlockingQueue<>(1, true);
		(new Thread(new Producer(drop))).start();
		(new Thread(new Consumer(drop))).start();
	}
}
