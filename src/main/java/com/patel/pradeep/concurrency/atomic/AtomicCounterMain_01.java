/**
 * 
 */
package com.patel.pradeep.concurrency.atomic;

/**
 * @author prade
 * OCA OCP Java SE 7 Programmer I & II Study Guide 2015
 * Apply Atomic Variables and Locks (OCP Objective 11.2) *
 */
public class AtomicCounterMain_01 {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		Counter counter = new Counter(); // shared object
		AtomicCounter atomicCounter = new AtomicCounter(); // shared object

		IncrementerThread it1 = new IncrementerThread(counter, atomicCounter);
		IncrementerThread it2 = new IncrementerThread(counter, atomicCounter);
		it1.start();
		it2.start();
		it1.join();
		it2.join();
		
		System.out.println("Final Normal Value->" + counter.getValue());
		System.out.println("Final Atomic Value->" + atomicCounter.getValue());

	}

}
