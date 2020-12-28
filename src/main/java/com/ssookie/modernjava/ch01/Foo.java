package com.ssookie.modernjava.ch01;

import java.sql.SQLOutput;
import java.util.function.*;

public class Foo {
    public static void main(String[] args) {

        /*
        람다 표현식 사용
         */
        RunSomething runSomething = (number) -> {
            return number + 10;
        };

        System.out.println(runSomething.doIt(1));

        /*
        Consumer Interface
         */
        Consumer<Integer> printCosumer = (i) -> System.out.println("Consumer 예제 출력: " + i);
        printCosumer.accept(11);    // Consumer 예제 출력: 11

        /*
        Function Interface
         */
        FunctionPlus10 plus10 = new FunctionPlus10(); // 인스턴스 만들어서 사용하면 됨
        System.out.println(plus10.apply(1));

        // lambda expression, 함수 조합
        Function<Integer, Integer> lambdaPlus10 = (number) -> number + 10;
        Function<Integer, Integer> multiply2 = (number) -> number * 2;
        System.out.println(lambdaPlus10.apply(1));
        System.out.println("Function compose 조합: " + lambdaPlus10.compose(multiply2).apply(4));   // compose 조합: 18
        System.out.println("Function andThen 조합: " + lambdaPlus10.andThen(multiply2).apply(4));  // andThen 조합: 28

        /*
        Predicate Interface
         */
        Predicate<String> startsWithS = (str) -> str.startsWith("S");
        System.out.println("Predicate 예제 출력: " + startsWithS.test("Ssookie"));  // Predicate 예제 출력: true

        /*
        Supplier Interface
         */
        Supplier<String> getString10 = () -> "Supplier : 인자 없이, 무조건 10을 리턴하는 인터페이스";
        System.out.println(getString10.get());  // Supplier : 인자 없이, 무조건 10을 리턴하는 인터페이스

        /*
        변수 캡쳐 (Variable Capture)
         */
        Foo foo = new Foo();
        foo.run();
    }

    private void run() {
        // 로컬 변수
        int baseNumber = 10;
        /*
        사실상 final 인 경우!(=Effective Final) 해당 변수를 변경하지 않는 경우 => 로컬/익명/람다 모두 참조 가능
        로컬/익명 클래스 => 쉐도잉 Y (클래스 내부에 동일한 이름의 변수가 있을 경우, 클래스 안에서 해당 변수값 사용)
        람다 => 쉐도잉 N (람다의 스콥은 람다를 감싸고 있는 스콥과 같음)
         */

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber); // 11 (쉐도잉)
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber); // accept인자의 baseNumber 출력 (쉐도잉)
            }
        };

        // 람다 => 람다와 로컬 변수 baseNumber는 같은 스콥임
        IntConsumer printInt = (num) -> {
            System.out.println(num);
        };

        // 같은 스콥 안에는 동일한 이름의 변수를 정의하지 못함 => 에러 발생
//        IntConsumer printInt2 = (baseNumber) -> {
//            System.out.println(baseNumber);
//        };

        printInt.accept(10);
    }
}
