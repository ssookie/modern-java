package com.ssookie.modernjava.ch06;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()-> {
            while(true){
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {  // 쓰레드가 깨어날 때
                    System.out.println("interrupted!");
                    return; // 종료
                }
            }
        });
        thread.start();

        System.out.println("Hello: " + Thread.currentThread().getName());
        Thread.sleep(3000L);
        thread.interrupt();
    }
}
