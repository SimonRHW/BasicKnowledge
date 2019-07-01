package com.simon.java.guide;

import java.util.concurrent.*;

public class Demo5to4 {

    public  static  void  main (String args[]){
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        int KEEP_ALIVE_TIME = 1;
        TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();
//        ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES,
//                NUMBER_OF_CORES*2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT,taskQueue,
//                new BackgroundThreadFactory(), new DefaultRejectedExecutionHandler());
    }
}
