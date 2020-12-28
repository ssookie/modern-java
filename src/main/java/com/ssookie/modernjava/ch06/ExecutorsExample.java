package com.ssookie.modernjava.ch06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsExample {
    public static void main(String[] args) {
        // ExecutorService: Executor를 상속 받은 인터페이스
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            System.out.println("Thread " + Thread.currentThread().getName());
        });

        // ExecutorService 는 다음 작업이 들어올때까지 계속 대기 -> 프로세스 죽지 않음 -> 명시적으로 종료 처리
        executorService.shutdown(); // graceful shutdown: 담당 작업을 끝까지 마치고 종료
        // cf) executorService.shutdownNow();   // no guarantee: 당장 종료
    }
}
