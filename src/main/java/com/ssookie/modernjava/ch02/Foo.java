package com.ssookie.modernjava.ch02;

public interface Foo {
    void printName();

    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꾸어 출력함.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    };

    static void printAnything() {
        System.out.println("Foo");
    }

    String getName();
}
