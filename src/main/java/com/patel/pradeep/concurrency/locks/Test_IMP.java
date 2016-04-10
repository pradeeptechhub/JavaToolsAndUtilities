package com.patel.pradeep.concurrency.locks;

import java.util.Random;
//Check My_Synchronized_Thread_VIMP.java
public class Test_IMP {
	public static void main(String[] args) throws Exception {
		/*new Thread(new Runnable() {
			public void run() {
				try {
					Test.m1();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					Test.m1();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					Test.m1();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		Thread.sleep(1000);*/

		new Thread(new Runnable() {
			public void run() {
				try {
					new Test_IMP().m2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					new Test_IMP().m2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				try {
					new Test_IMP().m2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	static Object staticLock = new Object(); //class level
	public static /*synchronized*/ void m1() throws Exception {
		//synchronized (Test.class) { //OR
		synchronized (staticLock) {
			// The following code is actually synchronized on Test.class object.
			System.out.println(Thread.currentThread().getName() + " m1 is started");
			Thread.sleep(new Random().nextInt(2000));
			System.out.println(Thread.currentThread().getName() + " m1 is completed");
		}
	}

	public void m2() throws InterruptedException {
		Object objectLock = new Object();
		Test_IMP classObjectLock = new Test_IMP();
		Thread.sleep(new Random().nextInt(1000));
		//synchronized (objectLock) { //Not working/synchronized
		//synchronized (classObjectLock) { //Not working/synchronized
		//synchronized (this) { //Not working/synchronized
		synchronized (staticLock) { //Works fine
			// other thread safe code
			System.out.println(Thread.currentThread().getName() + " ...");
			Thread.sleep(new Random().nextInt(2000));
			System.out.println(Thread.currentThread().getName() + " m2 is working...");
		}
		
		//Works fine
		/*synchronized (Test.class) {
			System.out.println(Thread.currentThread().getName() + " ***");
			Thread.sleep(new Random().nextInt(1000));
			System.out.println(Thread.currentThread().getName() + " m2 is working***"); // synchronized
		}*/
		//Thread.sleep(new Random().nextInt(1000));
		//System.out.println(Thread.currentThread().getName() + " m2 is completed");
	}
}