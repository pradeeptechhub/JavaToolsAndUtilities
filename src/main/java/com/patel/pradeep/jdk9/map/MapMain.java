package com.patel.pradeep.jdk9.map;

import java.util.Map;

public class MapMain {
    public static void main(String[] args) {

        //Iteration order not guaranteed

        //Up to 10 key/value
        System.out.println(Map.of("key1", 100, "key2", 200));

        //Unbounded
        System.out.println(Map.ofEntries(Map.entry("key1", true), Map.entry("key2", false)));
    }
}
/*
{key2=200, key1=100}
{key2=false, key1=true}
 */