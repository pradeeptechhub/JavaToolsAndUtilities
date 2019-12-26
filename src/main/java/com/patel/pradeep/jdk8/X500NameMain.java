package com.patel.pradeep.jdk8;

import sun.security.x509.X500Name;

import java.io.IOException;

//Add --add-exports=java.base/sun.security.x509=ALL-UNNAMED modules in Java Compiler under settings to compile
//This will not work with JDK9 unless above settings added
public class X500NameMain {
    public static void main(String[] args) throws IOException, IOException {
        X500Name name = new X500Name("CN=user");
        System.out.println("Hello->" + name.getCommonName());
        //Prints: Hello->user
    }
}
