package com.simon.java.performance;

public class GcTest {

    public static GcTest SAVE_HOOK = null;

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new GcTest();
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        //此时对象应该处于(reachable, finalized)状态
        if (null != SAVE_HOOK) {
            System.out.println("Yes 1, I am still alive");
        } else {
            System.out.println("No 1, I am dead");
        }
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (null != SAVE_HOOK) {
            System.out.println("Yes 2, I am still alive");
        } else {
            System.out.println("No 2, I am dead");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("execute method finalize()");
        SAVE_HOOK = this;
    }
}
