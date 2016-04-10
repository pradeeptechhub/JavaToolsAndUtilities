package com.patel.pradeep.concurrency.sync;

import com.patel.pradeep.utility.Threads;

/**
 * @author prade
 * http://www.dineshonjava.com/2013/05/static-synchronization-non-static.html#.VrYBsVkhZds
 */
public class StaticSyncDemo_01_VIMP {
	public static void main(String[] args) {
		Object obj = new Object();
		Account acct1 = new Account();
		Account acct2 = new Account();
		/*for(int i=0;i<50;i++){
			MyThread1 t1 = new MyThread1(acct1, obj);
			t1.setName("t1");
			t1.start();
			MyThread2 t2 = new MyThread2(acct1, obj);
			t2.setName("t2");
			t2.start();
			MyThread3 t3 = new MyThread3(acct2, obj);
			t3.setName("t3");
			t3.start();
			MyThread4 t4 = new MyThread4(acct2, obj);
			t4.setName("t4");
			t4.start();
		}*/
		MyThread1 t1 = new MyThread1(acct1, obj);
		MyThread2 t2 = new MyThread2(acct1, obj);
		MyThread3 t3 = new MyThread3(acct2, obj);
		MyThread4 t4 = new MyThread4(acct2, obj);
		t1.setName("t1");
		t2.setName("t2");
		t3.setName("t3");
		t4.setName("t4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

	static class Account {
		private static int staticCount = 0;
		private int count = 0;
		private static volatile int volatileCount = 0;
		//private static volatile AtomicInteger volatileCount = new AtomicInteger();
		static void showAccount(String accountName, Account acct, Object obj) {
			synchronized (acct) {
				staticMethod(accountName);
			}
			synchronized (Account.class) {
				staticCount++;
				try {Thread.sleep(500);} catch (Exception e) {}
				System.out.println(staticCount +
						" Account: " + accountName + " Holder: " + Thread.currentThread().getName());
				try {Thread.sleep(500);} catch (Exception e) {}
			}
			synchronized (obj) {
				staticCount++;
				try {Thread.sleep(500);} catch (Exception e) {}
				System.out.println(staticCount +
						" Account: " + accountName + " Holder: " + Thread.currentThread().getName());
				try {Thread.sleep(500);} catch (Exception e) {}
			}
		}
		private static void staticMethod(String accountName) {
			staticCount++;
			try {Thread.sleep(500);} catch (Exception e) {}
			System.out.println(staticCount +
					" Account name: " + accountName + " Holder: " + Thread.currentThread().getName());
			try {Thread.sleep(500);} catch (Exception e) {}
		}
		void showAcct(String accountName, Account acct, Object obj) {
			synchronized (acct) {
				printMethod(accountName, "acct");
			}
			/*synchronized (Account.class) {
				printMethod(accountName,"Account.class");
			}*/
			/*synchronized (obj) {
				printMethod(accountName,"obj");
			}*/
			/*synchronized (Object.class) {
				printMethod(accountName,"Object.class");
			}*/
			/*synchronized (this) {
				printMethod(accountName,"this");
			}*/
		}
		private void printMethod(String accountName, String s) {
			volatileCount++; 
			//volatileCount.getAndIncrement();
			staticCount++;count++;
			Threads.sleep(100, null);
			System.out.println(volatileCount+" " +staticCount + " " + count+ " ("+
					Thread.currentThread().getName() + ") Account("+s+"):" + " " + accountName);
		}
	}

	static class MyThread1 extends Thread {
		Account acct;
		Object obj;
		public MyThread1(Account acct, Object obj) {
			this.acct = acct;
			this.obj = obj;
		}
		public void run() {
			//Account.showAccount("Dineshonjava.com", acct,obj);
			acct.showAcct("Dineshonjava.com",acct,obj);
		}
	}
	static class MyThread2 extends Thread {
		Account acct;
		Object obj;
		public MyThread2(Account acct, Object obj) {
			this.acct = acct;
			this.obj = obj;
		}
		public void run() {
			//Account.showAccount("Linkedin.com",acct,obj);
			acct.showAcct("Linkedin.com",acct,obj);
		}
	}
	static class MyThread3 extends Thread {
		Account acct;
		Object obj;
		public MyThread3(Account acct, Object obj) {
			this.acct = acct;
			this.obj = obj;
		}
		public void run() {
			//Account.showAccount("Facebook.com",acct,obj);
			acct.showAcct("Facebook.com",acct,obj);
		}
	}
	static class MyThread4 extends Thread {
		Account acct;
		Object obj;
		public MyThread4(Account acct, Object obj) {
			this.acct = acct;
			this.obj = obj;
		}
		public void run() {
			//Account.showAccount("Twitter.com",acct,obj);
			acct.showAcct("Twitter.com",acct,obj);
		}
	}
}
