package com.simon.java.concurrent;

/**
 * 线程组是为了方便线程的管理
 * 线程池是为了管理线程的生命周期，复用线程，减少创建销毁线程的开销。
 *
 * @author Simon
 */
public class ThreadGroupTest {

    public static void main(String[] args) {
        // 获取主线程所在的线程组，这是所有线程默认的线程组
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的名字：" + mainGroup.getName());
        System.out.println("主线程组是否是后台线程组：" + mainGroup.isDaemon());
        new MyThread("主线程组的线程").start();
        System.out.println("主线程组线程数：" + mainGroup.activeCount());

        ThreadGroup otherThreadGroup = new ThreadGroup("新线程组");
        otherThreadGroup.setDaemon(true);
        System.out.println("otherThreadGroup是否是后台线程组：" + otherThreadGroup.isDaemon());
        new MyThread(otherThreadGroup, "otherThreadGroup的线程MyThread ：one").start();
        new MyThread(otherThreadGroup, "otherThreadGroup的线程MyThread ：two").start();
        System.out.println("otherThreadGroup线程组线程数：" + otherThreadGroup.activeCount());
    }

    static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        public MyThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(getName() + " 线程的i变量" + i);
            }
        }
    }
}


