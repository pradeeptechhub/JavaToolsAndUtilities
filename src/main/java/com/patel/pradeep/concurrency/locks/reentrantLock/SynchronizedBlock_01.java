/**
 * 
 */
package com.patel.pradeep.concurrency.locks.reentrantLock;

/**
 * @author prade
 * OCP Java SE 7 Programmer II Certification Guide 2015 11.2.2 (page 686)
 */
public class SynchronizedBlock_01 {
	public static void main(String[] args) {
		Inventory loc1 = new Inventory("Seattle");
		loc1.inStock = 100;
		Inventory loc2 = new Inventory("LA");
		loc2.inStock = 200;
		Shipment s1 = new Shipment(loc1, loc2, 1);
		Shipment s2 = new Shipment(loc2, loc1, 10);
		s1.start();
		s2.start();
	}
}

class Inventory {
	int inStock;
	String name;

	Inventory(String name) {
		this.name = name;
	}
	public void stockIn(long qty) {
		inStock += qty;
	}
	public void stockOut(long qty) {
		inStock -= qty;
	}
}

class Shipment extends Thread {
	Inventory loc1, loc2;
	int qty;

	Shipment(Inventory loc1, Inventory loc2, int qty) {
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.qty = qty;
	}

	public void run() {
		synchronized (loc1) {
			//SleepUtility.randomSleep(1000);
			synchronized (loc2) {
				loc2.stockOut(qty);
				loc1.stockIn(qty);
				System.out.println(Thread.currentThread().getName()+" "+loc1.inStock + ":" + loc2.inStock);
			}
		}
	}
}