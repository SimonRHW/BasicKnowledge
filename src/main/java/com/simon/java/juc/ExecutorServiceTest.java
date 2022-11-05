package com.simon.java.juc;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Simon
 */
public class ExecutorServiceTest {

    public static void main(String[] args) {
        cachedThreadPoolOOM();
    }

    public static void cachedThreadPoolOOM() {
        ThreadPoolExecutor fixedThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (int i = 1; i <= 1000000; i++) {
            final int taskId = i;
            System.out.println("execute task:" + taskId);
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程：" + Thread.currentThread().getName()
                                + " 正在执行 task: " + taskId);
                        // 任务耗时10秒
                        Thread.sleep(10000);
                    } catch (InterruptedException ignored) {
                    }
                }
            });
        }
        fixedThreadPool.shutdownNow();
    }
}
