package com.ssookie.modernjava.ch01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
    public static void main(String[] args) {
        // 람다 표현식 사용
        UnaryOperator<String> hi = (str) -> "hi " + str;

        // 1. static 메서드 참조 => 타입:메서드
        UnaryOperator<String> hihi = Greeting::hi; // Greeting 클래스의 hi 메서드 사용
        System.out.println(hihi.apply("ssookie"));

        // 2. 특정 객체의 인스턴스 메서드 참조 => 객체 레퍼런스:: 인스턴스 메서드
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("ssookie"));

        // 3-1. 생성자 참조 => 타입::new
        // 입력값 없음 -> Supplier
        Supplier<Greeting> newGreeting = Greeting::new; // Greeting() 참조
        newGreeting.get();  // 이 단계에서 생성자 만들어짐

        // 3-2. 입력값을 받는 생성자 참조
        // 입력값 String, 리턴값 Greeting -> Function
        Function<String, Greeting> ssookieGreeting = Greeting::new; // Greeting(String name) 참조
        Greeting ssookie = ssookieGreeting.apply("ssookie");
        System.out.println("ssookie's Name : "+ssookie.getName());

        // 4. 임의 객체의 인스턴스 매서드 참조 => 타입::인스턴스 메서드
        String[] names = {"ssookie", "dynee", "toby"};
        Arrays.sort(names, String::compareToIgnoreCase /* new Comparator<String>() {    // Comparator 가 함수형 인터페이스 -> 람다 사용 가능
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        }*/
        );
        System.out.println(Arrays.toString(names));
    }
}
