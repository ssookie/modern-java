package com.ssookie.modernjava.ch06;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsExample3 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(getRunnable("Hello!"), 1, 2, TimeUnit.SECONDS);
        // executorService.shutdown();  // 이거있으면 InterruptedException 발생하여 실행 안됨
    }

    private static Runnable getRunnable(String message) {
        return() -> System.out.println(message + Thread.currentThread().getName());
    }
}
