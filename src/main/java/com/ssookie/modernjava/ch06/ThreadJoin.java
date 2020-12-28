package com.ssookie.modernjava.ch06;

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()-> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread.start();

        // 메인 쓰레드가 위 쓰레드를 기다리게 함.
        System.out.println("Hello: " + Thread.currentThread().getName());
        thread.join();
        System.out.println(thread + " is finished.");
    }
}
