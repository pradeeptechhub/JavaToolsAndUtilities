/**
 * 
 */
package com.patel.pradeep.concurrency.collections.set;

import java.util.HashSet;
import java.util.Set;

/**
 * @author prade
 *
 */
public class HashSetTest {
final static int size = 10;
	public static void main(String[] args) {
		final Set<Long> generatedValues = new HashSet<>(size);
		System.out.println(generatedValues.add(5L));
		System.out.println(generatedValues.add(3L));
		System.out.println(generatedValues.add(5L));
		System.out.println(generatedValues.add(4L));}

}
