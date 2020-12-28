package com.ssookie.modernjava.ch06;

import java.util.concurrent.*;

public class CompletableFutureExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 리턴 타입 없는 경우
        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
            System.out.println("Hello runAsync: " + Thread.currentThread().getName());
        });
        System.out.println(future.get());   // get()이 호출 되어야지만 위 thread 작업이 실행 되는 것임

        // 리턴 타입 있는 경우
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() ->{
            System.out.println("Hello supplyAsync: " + Thread.currentThread().getName());
            return "Hello";
        });
        System.out.println(future2.get());

        // 콜백 함수
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() ->{
            System.out.println("Hello supplyAsync: " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply(s -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(future3.get());

        // 콜백 자체를 또 다른 쓰레드에서 실행
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future4 = CompletableFuture.supplyAsync(() -> {
            // Hello supplyAsync: pool-1-thread-1 -> executorService 을 사용하여 forkJoinPool 사용하지 않음
            System.out.println("Hello supplyAsync: " + Thread.currentThread().getName());
            return "Hello";
        }, executorService).thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
        }, executorService);

        executorService.shutdown();
    }
}
