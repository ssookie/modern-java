package com.ssookie.modernjava.ch02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class App {
    public static void main(String[] args) {
        /**
         *  Interface의 default/static 메서드
         */
        Foo foo = new DefaultFoo("ssookie");
        foo.printName();
        foo.printNameUpperCase();   // default method

        Foo.printAnything();    // static method

        /**
         * Java 8 - Default Method
         */
        List<String> names = new ArrayList<>();
        names.add("ssookie");
        names.add("dynee");
        names.add("soojin");

        // forEach()
        names.forEach(s -> System.out.println(s));   // Lambda Expression
        names.forEach(System.out::println);   // Method Reference

        // spliterator() : 쪼갤 수 있는 iterator
        Spliterator<String> spliterator = names.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        System.out.println("첫 번째 spliterator === ");
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("두 번째 spliterator === ");
        while(spliterator1.tryAdvance(System.out::println));

        // Comparator
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase);
        names.forEach(System.out::println);
        names.sort(compareToIgnoreCase.reversed());
        names.forEach(System.out::println);

    }
}
