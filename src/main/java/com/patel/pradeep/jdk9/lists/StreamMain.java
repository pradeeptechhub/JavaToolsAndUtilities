package com.patel.pradeep.jdk9.lists;

import java.util.List;
import java.util.stream.Stream;

public class StreamMain {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        Stream<Integer> ints = list.stream();
        ints.map(i -> i + 1)
                .filter(i -> i < 5)
                .forEach(obj -> System.out.print(obj + " "));

        System.out.println();

        List.of(1, 2, 3, 4, 5, 6).stream().map(i -> i + 1)
                .filter(i -> i < 5)
                .forEach(System.out::println);
    }
}
/*
2 3 4
2
3
4
 */