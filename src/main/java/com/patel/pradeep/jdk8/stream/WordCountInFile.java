package com.patel.pradeep.jdk8.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class WordCountInFile {
    public static void main(String[] args) throws IOException {
        Stream<String> lines = Files.lines(Paths.get("src/main/resources/index.html"), StandardCharsets.UTF_8);
        //lines.forEach(System.out::println);
        Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));
        System.out.println(words.filter(w -> w.trim().length() != 0).count());
        //OR
        //words.filter(w -> w.trim().length() != 0).forEach(obj -> System.out.println(obj));
    }
}
