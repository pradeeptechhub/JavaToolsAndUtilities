/**
 * 
 */
package com.patel.pradeep.concurrency.atomic;

/**
 * @author prade
 *
 */
public class Counter {
	private int count;

	public void increment() {
		count++; //non atomic
	}

	public int getValue() {
		return count;
	}
}
