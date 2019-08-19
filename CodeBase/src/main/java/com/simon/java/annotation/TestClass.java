package com.simon.java.annotation;


import java.lang.reflect.Method;

public class TestClass {

    public static void main(String[] args) {
        NoBug testobj = new NoBug();
        Class clazz = testobj.getClass();
        Method[] method = clazz.getDeclaredMethods();
        StringBuilder log = new StringBuilder();
        int errorTimes = 0;
        for (Method m : method) {
            if (m.isAnnotationPresent(TestAnnotation.class)) {
                try {
                    m.setAccessible(true);
                    m.invoke(testobj, null);
                } catch (Exception e) {
                    errorTimes++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
                }
            }
        }
        log.append(clazz.getSimpleName());
        log.append(" has  ");
        log.append(errorTimes);
        log.append(" error.");
        System.out.println(log.toString());
    }


}
