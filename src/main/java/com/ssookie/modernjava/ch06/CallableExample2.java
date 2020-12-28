package com.ssookie.modernjava.ch06;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableExample2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Callable<String> hello = () -> {
          Thread.sleep(2000L);
          return "Hello";
        };
        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };
        Callable<String> ssookie = () -> {
            Thread.sleep(1000L);
            return "Ssookie";
        };

        // 여러 작업 동시에 실행하기, 모두 끝나야 결과 가져옴
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, ssookie));
        for(Future<String> f : futures) {
            System.out.println(f.get()); // HelloJavaSsookie
        }

        // 여러 작업 중 하나라도 먼저 응답이 오면 끝내기
        String futures2 = executorService.invokeAny(Arrays.asList(hello, java, ssookie));
        System.out.println(futures2);    // Hello - single thread이기 때문에!

        executorService.shutdown();
    }
}
