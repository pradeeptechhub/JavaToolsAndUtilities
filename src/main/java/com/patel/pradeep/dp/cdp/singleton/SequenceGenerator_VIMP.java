/**
 * 
 */
package com.patel.pradeep.dp.cdp.singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @author prade http://www.javacreed.com/the-broken-singleton/
 */
public class SequenceGenerator_VIMP {
	static Map<Long,String> map = new ConcurrentHashMap<>();
	public static void main(String[] args) {
		final int THREAD_COUNT = 1000;
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT);
		final List<Thread> threads = new LinkedList<>();
		for (int i = 0; i < THREAD_COUNT; i++) {
			final Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						cyclicBarrier.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
					final SequenceGenerator sequenceGenerator = SequenceGenerator.getInstance();
					map.put(sequenceGenerator.getNextValue(),"");
				}
			});
			thread.start();
			threads.add(thread);
		}
		for (final Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		List<Long> list = new ArrayList<Long>(map.keySet());
		Collections.sort(list);
		System.out.println(list.size());
		System.out.println(list);
	}
}

class SequenceGenerator {
	private static class SingletonHolder {
		private static final SequenceGenerator INSTANCE = new SequenceGenerator(1L);
	}
	public static SequenceGenerator getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	private long nextValue;
	private SequenceGenerator() {}
	private SequenceGenerator(final long nextValue) {
		this.nextValue = nextValue;
	}

	public synchronized long getNextValue() {
		return nextValue++;
	}
}