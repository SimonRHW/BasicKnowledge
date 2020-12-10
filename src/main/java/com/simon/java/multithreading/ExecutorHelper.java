package com.simon.java.multithreading;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorHelper {
    private static LinkedBlockingQueue<Test> taskQueue = new LinkedBlockingQueue<Test>();
    private static AtomicBoolean runningState = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <5; i++) {
            taskQueue.put(new Test(i, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(Thread.currentThread().getName() + "  onFailure  " + System.currentTimeMillis());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println(Thread.currentThread().getName() + "  onResponse  " + System.currentTimeMillis());
                }
            }));
            THREAD_POOL_EXECUTOR.execute(task);
        }
        Thread.sleep(20000);
        taskQueue.put(new Test(5, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(Thread.currentThread().getName() + "  onFailure  " + System.currentTimeMillis());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(Thread.currentThread().getName() + "  onResponse  " + System.currentTimeMillis());
            }
        }));
        THREAD_POOL_EXECUTOR.execute(task);
    }

    /**
     * 进行安装处理
     */
    static Runnable task = () -> {
        try {
            Test poll = taskQueue.poll();
            if (poll != null) {
                if (poll.tag % 2 == 0) {
                    poll.callback.onFailure(null, null);
                } else {
                    poll.callback.onResponse(null, null);
                }
                System.out.println(Thread.currentThread().getName() + "  0000  " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "  1111  " + System.currentTimeMillis());
            } else {
                System.out.println(Thread.currentThread().getName() + "  2222  " + System.currentTimeMillis());
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    };


    public static final Executor THREAD_POOL_EXECUTOR;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "simon #" + mCount.getAndIncrement());
        }
    };

    static {
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
                1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                THREAD_FACTORY, new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    static class Test {
        private int tag;
        private Callback callback;

        public Test(int tag, Callback callback) {
            this.tag = tag;
            this.callback = callback;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public Callback getCallback() {
            return callback;
        }

        public void setCallback(Callback callback) {
            this.callback = callback;
        }
    }
}
