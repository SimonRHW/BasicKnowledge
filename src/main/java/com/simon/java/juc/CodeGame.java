package com.simon.java.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class CodeGame {

    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> queue = new SynchronousQueue<>();
        System.out.println(queue.offer(1) + " ");
        System.out.println(queue.offer(2) + " ");
        System.out.println(queue.offer(3) + " ");
        //会阻塞线程
        //System.out.println(queue.take() + " ");
        System.out.println(queue.size());
    }
}
