package com.ssookie.modernjava.ch01;

@FunctionalInterface
public interface RunSomething {
    int doIt(int num);
    // void doAgain(); // 함수형 인터페이스이므로 추상 메서드가 반드시 1개여야 함.
}
