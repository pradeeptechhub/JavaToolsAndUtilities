package com.patel.pradeep.jdk9.stream;

import lombok.Data;

import java.util.Set;

@Data
public class Book {
    public final String title;
    public final Set<String> authors;
    public final double price;

    public Book(String title, Set<String> authors, double price) {
        this.title = title;
        this.authors = authors;
        this.price = price;
    }

    public static Book getBook(){
        return new Book("Hell", Set.of("Pradeep Patel","Krishna Yadav"), 10.5);
    }

}
