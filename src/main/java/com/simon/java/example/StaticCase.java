package com.simon.java.example;

/**
 * @author Simon
 */
public class StaticCase {

    static int staticValue = 100;

    public static void main(String[] args) {
        new StaticCase().accessStaticValue();
    }

    //静态成员变量虽然独立于对象，但是不代表不可以通过对象去访问，所有的静态方法和静态变量都可以通过对象访问（只要访问权限足够）
    private void accessStaticValue() {
        int staticValue = 10;
        System.out.println(this.staticValue);
    }
}


class InitializationTest {

    final static InitializationTest INSTANCE = new InitializationTest();

    static {
        System.out.println(String.format("static initialization block invoked, instance = %s", INSTANCE));
    }

    {
        System.out.println(String.format("initialization block invoked, instance = %s", INSTANCE));
    }

    InitializationTest() {
        System.out.println(String.format("constructor invoked, instance = %s", INSTANCE));
    }

    public static void main(String[] a) {
        new InitializationTest();
    }
}

class A {
    static {
        System.out.println("A initializing......");
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new B();
    }
}

class B {
    static {
        System.out.println("B initializing......");
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new A();
    }
}

class InitializationLockTest {

    public static void main(String[] a) throws Exception {
        new Thread() {
            @Override
            public void run() {
                new A();
            }
        }.start();
//        new A();
        new B();
        System.out.println("Done.");
    }
}