package com.patel.pradeep.jdk8.stream;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.stream.Stream;

public class CollectorsMain {
    public static void main(String[] args) {
        List<Integer> integers = Stream.of(1, 2, 3, 4, 5, 6).map(i -> i + 1).collect(toList());
        System.out.println(integers);

        Map<Integer, List<Integer>> integerListMap = Stream.of(1, 4, 5, 7, 8, 9).collect(groupingBy(i -> i % 2, toList()));
        System.out.println(integerListMap);
        System.out.println("EVEN->" + integerListMap.get(0));
        System.out.println("ODD->" + integerListMap.get(1));
    }
}
/*
[2, 3, 4, 5, 6, 7]
{0=[4, 8], 1=[1, 5, 7, 9]}
EVEN->[4, 8]
ODD->[1, 5, 7, 9]
 */