package com.ssookie.modernjava.ch03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPIApp {
    public static void main(String[] args) {
        List<OnlineClass> spingClasses = new ArrayList<>();
        spingClasses.add(new OnlineClass(1, "spring boot", true));
        spingClasses.add(new OnlineClass(2, "spring data jpa", true));
        spingClasses.add(new OnlineClass(3, "spring mvc", false));
        spingClasses.add(new OnlineClass(4, "spring core", false));
        spingClasses.add(new OnlineClass(5, "rest api development", false));

        /**
         * 걸러내기: Filter(Predicate)
         */
        System.out.println("=== spring으로 시작하는 수업 ===");
        spingClasses.stream().filter(c -> c.getTitle().startsWith("spring"))
                .forEach(c -> System.out.println(c.getTitle()));

        System.out.println("=== close 되지 않은 수업 ===");
        spingClasses.stream().filter(c -> !c.isClosed())
                .forEach(c -> System.out.println(c.getTitle()));
        // method reference: 임의 객체의 인스턴스 매서드 참조 => 타입::인스턴스 메서드
        spingClasses.stream().filter(Predicate.not(OnlineClass::isClosed))
                .forEach(c -> System.out.println(c.getTitle()));

        /**
         * 변경하기: Map(Function)
         */
        System.out.println("=== 수업 이름만 모아서 스트림 만들기 ===");
        spingClasses.stream().map(c -> c.getTitle())
                .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(1, "The Java, Test", true));
        javaClasses.add(new OnlineClass(2, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(3, "The Java, 8 to 11", false));

        List<List<OnlineClass>> events = new ArrayList<>();
        events.add(spingClasses);
        events.add(javaClasses);

        /**
         * 변경하기: FlatMap(Function)
         */
        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        events.stream()
                .flatMap(Collection::stream)    // events -> OnlineClass 타입으로 변환됨
                .forEach(c -> System.out.println(c.getId()));

        /**
         * 제한하기: limit(long) 또는 skip(long)
         */
        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서, 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1); // 중간 연산 -> 아무 현상 발생하지 않음.
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        /**
         * 스트림에 있는 데이터가 특정 조건을 만족하는지 확인: anyMatch(), allMatch(), nonMatch()
         */
        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream().anyMatch(c -> c.getTitle().contains("Test"));
        System.out.println(test);

        /**
         * 개수 세기: count()
         */
        System.out.println("자바 수업 중에 Test가 들어있는 수업의 갯수 확인");
        long count = javaClasses.stream()
                .filter(c -> c.getTitle().contains("Test"))
                .count();
        System.out.println(count);

        /**
         * 스트림을 데이터 하나로 뭉치기: reduce(identity, BiFunction), collect(), sum(), max()
         */
        System.out.println("스프링 수업 중 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        List<String> spring = spingClasses.stream()
                .filter(c -> c.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
        // 위에서 filter 와 map 의 순서를 바꾸어 주면, type 이 바뀜
        List<String> spring1 = spingClasses.stream()
                .map(OnlineClass::getTitle) // String type
                .filter(s -> s.contains("Test"))
                .collect(Collectors.toList());
        spring.forEach(System.out::println);
    }
}
