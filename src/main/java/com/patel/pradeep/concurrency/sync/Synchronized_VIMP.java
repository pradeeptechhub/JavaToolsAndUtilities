/**
 * 
 */
package com.patel.pradeep.concurrency.sync;

import com.patel.pradeep.utility.Threads;

/**
 * @author prade
 *         https://javainterviewsquestions.wordpress.com/category/senior-java-
 *         developer-interviews/page/2/
 * 
 *         Can a thread call a non-synchronized instance method of an Object
 *         when a synchronized method is being executed ? Yes, a Non
 *         synchronized method can always be called without any problem.
 * 
 *         Can two threads call two different synchronized instance methods of
 *         an Object? No. If a object has synchronized instance methods then the
 *         Object itself is used a lock object for controlling the
 *         synchronization.
 */
public class Synchronized_VIMP extends Thread {
	private int id = 0;
	private Common common;

	public Synchronized_VIMP(String name, int no, Common object) {
		super(name);
		common = object;
		id = no;
	}

	public void run() {
		try {
			if (id == 0) {
				common.normalMethod0(this.getName());
			} else if (id == 1){
				common.normalMethod1(this.getName());
			} else if(id == 2){
				Common.staticMethod2(this.getName());
			} else{
				Common.staticMethod3(this.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Common c1 = new Common();
		Common c2 = new Common();
		Synchronized_VIMP c1t1 = new Synchronized_VIMP("MyThread-c1-0", 0, c1);
		//Synchronized_VIMP c1t2 = new Synchronized_VIMP("MyThread-c1-1", 1, c1);
		Synchronized_VIMP c1t3 = new Synchronized_VIMP("MyThread-c1-2", 2, c1);
		//Synchronized_VIMP c1t4 = new Synchronized_VIMP("MyThread-c1-3", 3, c1);
		
		//Synchronized_VIMP c2t1 = new Synchronized_VIMP("MyThread-c2-0", 0, c2);
		//Synchronized_VIMP c2t2 = new Synchronized_VIMP("MyThread-c2-1", 1, c2);
		//Synchronized_VIMP c2t3 = new Synchronized_VIMP("MyThread-c2-2", 2, c2);
		//Synchronized_VIMP c2t4 = new Synchronized_VIMP("MyThread-c2-3", 3, c2);
		c1t1.start();
		//c1t2.start();
		c1t3.start();
		//c1t4.start();
		
		//c2t1.start();
		//c2t2.start();
		//c2t3.start();
		//c2t4.start();
	}
}

class Common {
	public synchronized void normalMethod0(String name) {
		commonMethod(name,"normalMethod0");
	}
	public synchronized void normalMethod1(String name) {
		commonMethod(name,"normalMethod1");
	}
	public static synchronized void staticMethod2(String name) {
		commonStaticMethod(name,"staticMethod2");
	}
	public static synchronized void staticMethod3(String name) {
		commonStaticMethod(name,"staticMethod3");
	}
	private void commonMethod(String name, String s) {
		System.out.println("-----"+s+" called " + name);
		Threads.sleepRandom(1000);//randomSleepWithThreadName
		System.out.println("-----"+s+"   done " + name);
	}
	private static void commonStaticMethod(String name, String s) {
		System.out.println("*****"+s+" called " + name);
		Threads.sleepRandom(2000);//randomSleepWithThreadName
		System.out.println("*****"+s+"   done " + name);
	}
}