package com.ssookie.modernjava.ch06;

public class ThreadSleep {
    public static void main(String[] args) {
        Thread thread = new Thread(()-> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {  // 쓰레드가 깨어날 때
                e.printStackTrace();
            }
            System.out.println("Thread: " + Thread.currentThread().getName());
        });
        thread.start();

        System.out.println("Hello: " + Thread.currentThread().getName());
    }
}
