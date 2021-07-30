package com.simon.java.multithreading;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 本身并不存储值， ThreadLocalMap是使用ThreadLocal的弱引用作为key的。
 * 一个对象若只剩下弱引用，则该对象在GC是就会被回收。
 * 内存泄漏解决方法：调用remove  默认是值传递
 */
public class ThreadLocalTest {
    public static final Integer SIZE = 500;

    static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(5, 5, 1,
                    TimeUnit.MINUTES, new LinkedBlockingDeque<>());

    static class Stu {
        //5M 内存
        private byte[] local = new byte[1024 * 1024 * 5];
    }

    static ThreadLocal<Stu> local = new ThreadLocal<>();

    public static void main(String[] args) {

        try {
            for (int i = 0; i < SIZE; i++) {
                threadPoolExecutor.execute(() -> {
                    local.set(new Stu());
                    System.out.println("开始执行");
                });
                Thread.sleep(100);
            }
            local = null; //设置为null ，依旧会造成内存泄漏 会有25兆堆内存泄露
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
