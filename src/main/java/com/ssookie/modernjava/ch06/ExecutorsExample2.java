package com.ssookie.modernjava.ch06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsExample2 {
    public static void main(String[] args) {
        // 2개의 쓰레드 번갈아가면서 실행
        // ExecutorService 내부에서 Blocking Queue -> Thread Pool 로 task 를 보냄
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(getRunnable("Hello"));
        executorService.submit(getRunnable("Ssookie"));
        executorService.submit(getRunnable("The"));
        executorService.submit(getRunnable("Java"));
        executorService.submit(getRunnable("Thread"));

        executorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return() -> System.out.println(message + Thread.currentThread().getName());
    }
}
