package com.simon.java.concurrent;

/**
 * @author Simon
 */
public class TreadTest {

    public static void main(String[] agrs) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(300);
                            System.out.println("hello " + i + " ::::::" + Thread.currentThread());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
