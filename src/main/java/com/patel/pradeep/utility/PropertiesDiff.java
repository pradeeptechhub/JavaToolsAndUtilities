package com.patel.pradeep.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class PropertiesDiff {
    public static void main(String[] args) throws IOException {
        Map<String, Object> out = new LinkedHashMap<>();
        Map<String, Object> expected = new LinkedHashMap<>();
        Files.lines(Paths.get("src/main/resources/config2.properties"))
                .filter(a -> {
                    return !a.isBlank() && !a.trim().startsWith("#");
                }).map(m -> {
            String[] abc = m.split("=");
            if (abc.length == 2) {
                out.put(abc[0].trim(), abc[1].trim());
            }
            return m;
        }).forEach(System.out::println);

        Files.lines(Paths.get("src/main/resources/config.properties"))
                .filter(a -> {
                    return !a.isBlank() && !a.trim().startsWith("#");
                }).map(m -> {
            String[] abc = m.split("=");
            if (abc.length == 2) {
                expected.put(abc[0].trim(), abc[1].trim());
            }
            return m;
        }).forEach(System.out::println);

        System.out.println(out);
        System.out.println(expected);

        for(String key : expected.keySet()){
            if(out.get(key) == null) {
                System.out.println(key);
            }else{
                System.out.println(key+"="+out.get(key));
            }
        }
    }
}
