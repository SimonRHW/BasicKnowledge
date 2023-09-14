package com.simon.java.multithreading;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TreadPoolTest {

    public static volatile int flag = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            THREAD_POOL_EXECUTOR.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程：" + Thread.currentThread().getName()
                            + " start" + finalI);
                    while (true) {
                        if (flag > 0) {
                            break;
                        }
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程：" + Thread.currentThread().getName()
                            + " end" + finalI);
                }
            });
        }

        try {
            Thread.sleep(5000);
            flag = 1;
            Thread.sleep(8000);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static final Executor THREAD_POOL_EXECUTOR;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "Simon #" + mCount.getAndIncrement());
        }
    };

    static {
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
                1, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
                sThreadFactory, new ThreadPoolExecutor.DiscardOldestPolicy());
    }

}
