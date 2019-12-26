package com.patel.pradeep.jdk9.lists;

import java.util.List;

public class ArrayListMain {
    public static void main(String[] args) {

        //Iteration order guaranteed

        List<Integer> lists = List.of(1, 2, 3, 4, 5);
        System.out.println(lists);
        System.out.println(lists.getClass());
        try {
            lists.add(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            lists.remove(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
[1, 2, 3, 4, 5]
class java.util.ImmutableCollections$ListN
java.lang.UnsupportedOperationException
	at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:71)
	at java.base/java.util.ImmutableCollections$AbstractImmutableCollection.add(ImmutableCollections.java:75)
	at com.patel.pradeep.jdk9.lists.ArrayListMain.main(ArrayListMain.java:11)
java.lang.UnsupportedOperationException
	at java.base/java.util.ImmutableCollections.uoe(ImmutableCollections.java:71)
	at java.base/java.util.ImmutableCollections$AbstractImmutableList.remove(ImmutableCollections.java:107)
	at com.patel.pradeep.jdk9.lists.ArrayListMain.main(ArrayListMain.java:16)
 */