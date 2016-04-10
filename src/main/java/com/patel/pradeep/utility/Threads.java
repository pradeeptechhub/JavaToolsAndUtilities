/**
 * 
 */
package com.patel.pradeep.utility;

import java.util.Random;

/**
 * @author prade
 *
 */
public class Threads {
	
	public static int sleep(int s){
		try {
			Thread.sleep(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public static int sleep(int s, String threadName){
		try {
			System.out.println(threadName + " " + Thread.currentThread().getName()+" Sleeping for: " + s + " ms.");
			Thread.sleep(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public static int sleepRandom(int no, String threadName){
		int randomNo = new Random().nextInt(no);
		try {
			System.out.println(threadName + " " + Thread.currentThread().getName()+" Sleeping for: " + randomNo + " ms.");
			Thread.sleep(randomNo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return randomNo;
	}
	public static int sleepRandom(int no){
		int randomNo = new Random().nextInt(no);
		try {
			Thread.sleep(randomNo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return randomNo;
	}
		
}
