/**
 * 
 */
package com.patel.pradeep.concurrency.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author prade http://www.ibm.com/developerworks/library/j-5things4/
 * 
 *         A BlockingQueue in which each insert operation must wait for a
 *         corresponding remove operation by another thread, and vice versa. A
 *         synchronous queue does not have any internal capacity, not even a
 *         capacity of one (single object).
 * 
 *         The implementation code looks almost identical to ArrayBlockingQueue,
 *         but the application has an added benefit, in that SynchronousQueue
 *         will allow an insert into the queue only if there is a thread waiting
 *         to consume it.
 * 
 *         Obvious choice when you need a task-handoff design where one thread
 *         needs to sync with another one.
 */
public class SynchronousQueue_01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BlockingQueue<String> drop = new SynchronousQueue<String>();
		(new Thread(new Producer(drop))).start();
		(new Thread(new Consumer(drop))).start();
	}
}
