package com.ssookie.modernjava.ch04;

import com.ssookie.modernjava.ch03.OnlineClass;

import java.util.*;
import java.util.stream.IntStream;

public class OptionalApp {
    public static void main(String[] args) {
        List<com.ssookie.modernjava.ch03.OnlineClass> spingClasses = new ArrayList<>();
        spingClasses.add(new com.ssookie.modernjava.ch03.OnlineClass(1, "spring boot", true));
        spingClasses.add(new OnlineClass(2, "spring data jpa", true));

        // Optional 만들기
        Optional<OnlineClass> optional = spingClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        // Optional에 값이 있는지 없는지 확인하기
        System.out.println(optional.isPresent());

        // Optional에 있는 값 가져오기
        // 값 없을 때, Exception in thread "main" java.util.NoSuchElementException: No value present 발생함
        // System.out.println(optional.get());

        // -> Optional에 값이 있는지 확인한 후 작업하도록 권장
        if(optional.isPresent()) {
            System.out.println(optional.get());
        }

        /**
         * Optional을 이용한 다양한 로직 처리
         */
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        OnlineClass onlineClass = optional.orElse(createNewClass());
        System.out.println(onlineClass.getTitle());

        Optional<OnlineClass> onlineClass1 = optional.filter(oc -> oc.getId() > 10);

        Optional<Integer> integer = optional.map(OnlineClass::getId);
        System.out.println(integer.isPresent());
    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New Class", false);
    }
}
