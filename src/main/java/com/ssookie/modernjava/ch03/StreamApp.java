package com.ssookie.modernjava.ch03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApp {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("ssookie");
        names.add("dynee");
        names.add("soojin");

        /**
         * stream은 데이터 소스를 변경하는 것이 아님.
         */
        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        names.forEach(System.out::println); // names 그대로 출력

        /**
         * 중간 연산은 lazy하다.
         * 중간 연산은 최종 연산이 오기 전 까지 실행하지 않음
         * 최종 연산이 실행되기 전까지, 중간 연산은 무의미함
         */
        names.stream().map((s) ->{
            System.out.println(s);  // 출력하지 않음. 정의만 했을 뿐, 실행하지 않음.
            return s.toUpperCase();
        });
        System.out.println("=========");
        names.forEach(System.out::println);

        List<String> collect = names.stream().map((s) ->{
            System.out.println(s);  // 출력함
            return s.toUpperCase();
        }).collect(Collectors.toList());    // 최종 연산이 실행되기 때문에

        System.out.println("=========");
        collect.forEach(System.out::println);

        /**
         * 손쉽게 병렬 처리할 수 있다.
         */
         names.parallelStream().map(String::toUpperCase)
                 .collect(Collectors.toList());
         collect.forEach(System.out::println);

    }
}
