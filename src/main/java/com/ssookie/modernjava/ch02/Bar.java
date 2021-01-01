package com.ssookie.modernjava.ch02;

public interface Bar extends Foo {

    /**
     * Bar 에서는, Foo에서 제공하는 default method인 printNameUpperCase 를 제공하고 싶지 않을때
     * => 다시 추상메서드로 선언
     */

    void printNameUpperCase();

}
