package com.patel.pradeep.jdk8;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;

public class XmlBindMain {
    public static void main(String[] args) {
        String originalInput = "test input";
        String encodedString = new String(Base64.getEncoder().encode(originalInput.getBytes()));
        String decodedString = new String(Base64.getDecoder().decode(encodedString.getBytes()));
        System.out.println(encodedString);
        System.out.println(decodedString);
        System.out.println(new String(DatatypeConverter.parseBase64Binary(encodedString)));
    }
}
