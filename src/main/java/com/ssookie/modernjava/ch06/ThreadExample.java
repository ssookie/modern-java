package com.ssookie.modernjava.ch06;

public class ThreadExample {
    static class MyThread extends Thread {
        @Override
        public void run(){
            System.out.println("Hello: "  + Thread.currentThread().getName());
        }
    }
    public static void main(String[] args) {
        // main thread
        System.out.println(Thread.currentThread().getName());

        /*
        Thread 생성 방법 1. Thread 상속
         */
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("Hello");
        //Hello
        //Hello: Thread-0 // 쓰레드는 순서를 보장하지 않음

        /*
        Thread 생성 방법 2. Runnable 구현 또는 람다
         */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread's name: " + Thread.currentThread().getName());
            }
        });
        thread.start();
        System.out.println("Thread: "  + Thread.currentThread().getName());

        Thread threadLambda = new Thread(()->System.out.println("ThreadLambda's name: " + Thread.currentThread().getName()));
        threadLambda.start();
        System.out.println("ThreadLambda: " + Thread.currentThread().getName());

    }
}
