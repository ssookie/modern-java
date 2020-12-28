package com.ssookie.modernjava.ch06;

import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone());   // 작업 상태 확인, 완료시 true
        System.out.println("Started!");

        String result = helloFuture.get();   // 블록킹 콜, 결과값을 가져올 때까지 대기

        System.out.println(helloFuture.isDone());
        System.out.println("End!!");
        System.out.println("Result: " + result);
        executorService.shutdown();
    }
}
