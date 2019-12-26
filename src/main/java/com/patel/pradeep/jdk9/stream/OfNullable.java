package com.patel.pradeep.jdk9.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.patel.pradeep.jdk9.stream.Book.getBook;
import static java.util.stream.Collectors.toList;

public class OfNullable {
    public static void main(String[] args) {
        //JDK9: ofNullable
        long zero = Stream.ofNullable(null).count();
        long one = Stream.ofNullable(getBook()).count();
        System.out.printf("zero: %d, one: %d", zero, one);
        System.out.println();

        //JDK8: Before ofNullable
        Book book = getPossiblyNull(true);
        Stream<String> authors = book == null ? Stream.empty() : book.authors.stream();
        System.out.println("zero: " + authors.count());
        System.out.println();

        Book book1 = getPossiblyNull(false);
        Stream<String> authors1 = book1 == null ? Stream.empty() : book1.authors.stream();
        authors1.forEach(System.out::println);
        System.out.println();

        //JDK9: With ofNullable
        Stream.ofNullable(getPossiblyNull(false)).flatMap(b -> b.authors.stream()).forEach(System.out::println);
        Stream.ofNullable(getPossiblyNull(true)).flatMap(b -> b.authors.stream()).forEach(System.out::println);

    }

    private static Book getPossiblyNull(boolean isNull) {
        return isNull ? null : getBook();
    }
}

/*
zero: 0, one: 1
zero: 0

Krishna Yadav
Pradeep Patel

Krishna Yadav
Pradeep Patel
 */