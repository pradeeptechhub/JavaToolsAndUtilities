package com.patel.pradeep.jdk9.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FindGitConflict {
    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("src/main/resources/index.html"))
                .dropWhile(l -> !l.contains("<<<<<<<"))
                .skip(1)
                .takeWhile(l -> !l.contains(">>>>>>>"))
                .forEach(System.out::println);
    }
}
