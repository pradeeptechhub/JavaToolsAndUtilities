/**
 * 
 */
package com.patel.pradeep.concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author prade
 *
 */
public class AtomicCounter {
	private AtomicInteger count = new AtomicInteger();

	public void increment() {
		count.getAndIncrement(); //atomic operation
	}

	public int getValue() {
		return count.intValue();
	}
}
