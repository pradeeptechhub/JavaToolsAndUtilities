/**
 * 
 */
package com.patel.pradeep.concurrency.semaphore;

import com.patel.pradeep.utility.Threads;

/**
 * @author prade
 *
 */
public class Teller {
	static void getService(int tocken) {
		System.out.println(Thread.currentThread().getName() + " Serving TOKEN " + tocken + " for "
				+ Threads.sleepRandom(100) + " ms.");
	}
}
