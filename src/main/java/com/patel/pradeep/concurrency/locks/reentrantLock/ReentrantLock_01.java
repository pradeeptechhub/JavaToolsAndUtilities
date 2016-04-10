/**
 * 
 */
package com.patel.pradeep.concurrency.locks.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.patel.pradeep.utility.Threads;

/**
 * @author prade
 * OCP Java SE 7 Programmer II Certification Guide 2015 11.2.2 (page 687)
 */
public class ReentrantLock_01 {
	public static void main(String[] args) {
		Inventory_1 loc1 = new Inventory_1("Seattle");
		loc1.inStock = 100;
		Inventory_1 loc2 = new Inventory_1("LA");
		loc2.inStock = 200;
		Shipment_1 s1 = new Shipment_1(loc1, loc2, 1);
		Shipment_1 s2 = new Shipment_1(loc2, loc1, 10);
		s1.start();
		s2.start();
	}
}

class Inventory_1 {
	int inStock;
	String name;
	Lock lock = new ReentrantLock();

	Inventory_1(String name) {
		this.name = name;
	}
	public void stockIn(long qty) {
		inStock += qty;
	}
	public void stockOut(long qty) {
		inStock -= qty;
	}
}

class Shipment_1 extends Thread {
	Inventory_1 loc1, loc2;
	int qty;

	Shipment_1(Inventory_1 loc1, Inventory_1 loc2, int qty) {
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.qty = qty;
	}

	public void run() {
		/*try {
			if (loc1.lock.tryLock(SleepUtility.randomNoWithThreadName(1000), TimeUnit.MILLISECONDS)) {
				System.out.println(Thread.currentThread().getName() + " locked loc1 & SLEEPING for " + SleepUtility.randomSleepWithoutThreadName(10));
				if (loc2.lock.tryLock(SleepUtility.randomNoWithThreadName(1000), TimeUnit.MILLISECONDS)) {
					System.out.println(Thread.currentThread().getName() + " Locked both 1+2 "+loc1.name+" & " +loc2.name);
					loc2.stockOut(qty);
					loc1.stockIn(qty);
					System.out.println(Thread.currentThread().getName()+" "+ loc1.inStock + ":" + loc2.inStock);
					loc2.lock.unlock();
					loc1.lock.unlock();
				} else{
					System.out.println(Thread.currentThread().getName()+" Locking false:" + loc2.name);
				}
			} else
				System.out.println(Thread.currentThread().getName()+" Locking false:" + loc1.name);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		boolean lock1 = false;
		boolean lock2 = false;
		try {
			lock1 = loc1.lock.tryLock(Threads.sleepRandom(1000,""), TimeUnit.MILLISECONDS);
			lock2 = loc2.lock.tryLock(Threads.sleepRandom(1000,""), TimeUnit.MILLISECONDS);
			if (lock1 && lock2) {
				System.out.println(Thread.currentThread().getName() + " Locked both 1+2 "+loc1.name+" & " +loc2.name);
				loc2.stockOut(qty);
				loc1.stockIn(qty);
				System.out.println(Thread.currentThread().getName()+" "+ loc1.inStock + ":" + loc2.inStock);
			} else{
				System.out.println(Thread.currentThread().getName()+" Locking false:" + loc1.name);
				System.out.println(Thread.currentThread().getName()+" Locking false:" + loc2.name);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			//OCA OCP Java SE 7 Programmer I & II Study Guide (Exams 1Z0-803 & 1Z0-804) 2015 (page 793)
			if(lock1)loc1.lock.unlock();
			if(lock2)loc2.lock.unlock();
		}
	}
}