package com.patel.pradeep.concurrency.locks.rWRLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.patel.pradeep.utility.Threads;

// http://www.sourcetricks.com/2014/09/java-reentrantreadwritelock.html#.VrTuxlkhZds
public class ReadWriteLockDemo_01_VIMP {
	// Shared resources to be guarded
	private static int count = 0;

	public static void main(String[] args) {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		Thread producer = new Thread(new ProducerTask(lock));
		Thread anotherReader = new Thread(new AnotherReadTask(lock));
		Thread reader = new Thread(new ReadTask(lock));
		producer.start();
		reader.start();
		anotherReader.start();
	}

	// Producer thread. Counts up the resource.
	private static class ProducerTask implements Runnable {
		private ReentrantReadWriteLock lock = null;
		ProducerTask(ReentrantReadWriteLock lock) {
			this.lock = lock;
		}
		@Override
		public void run() {
			System.out.println("Producer requesting lock");
			lock.writeLock().lock();
			System.out.println("Producer gets lock");
			for (int i = 0; i < 5; i++) {
				count = count + 1;
				System.out.println("Counting up");
			}
			Threads.sleepRandom(5000, "Producer");			
			System.out.println("Producer releases lock");
			lock.writeLock().unlock();
		}
	}

	// Reader task
	private static class AnotherReadTask implements Runnable {
		private ReentrantReadWriteLock lock = null;
		AnotherReadTask(ReentrantReadWriteLock lock) {
			this.lock = lock;
		}
		@Override
		public void run() {
			System.out.println("AnotherReader requesting lock");
			lock.readLock().lock();
			System.out.println("AnotherReader gets lock");
			System.out.println("Current count = " + count);
			Threads.sleepRandom(500, "AnotherReader");			
			System.out.println("Another Reader releases lock");
			lock.readLock().unlock();
		}
	}

	// Reader task
	private static class ReadTask implements Runnable {
		private ReentrantReadWriteLock lock = null;
		ReadTask(ReentrantReadWriteLock lock) {
			this.lock = lock;
		}
		@Override
		public void run() {
			System.out.println("Reader requesting lock");
			lock.readLock().lock();
			System.out.println("Reader gets lock");
			System.out.println("Current count = " + count);
			Threads.sleepRandom(500, "Reader");			
			System.out.println("Reader releases lock");
			lock.readLock().unlock();
		}
	}
}
