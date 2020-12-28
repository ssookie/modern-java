package com.ssookie.modernjava.ch06;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        // 결과를 참조하기 위해 아래 CompletableFuture를 메서드로 만듦.
//        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
//            System.out.println("World " + Thread.currentThread().getName());
//            return "World";
//        });

        CompletableFuture<String> future = hello.thenCompose(s -> getWorld(s)); // hello.thenCompose(CompletableFutureExample2.getWorld);
        System.out.println(future.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message +" World";
        });
    }
}
