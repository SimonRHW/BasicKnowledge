package com.simon.java.annotation;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Simon
 */
public class TestClass {

    public static void main(String[] args) throws IllegalAccessException {
        Object object = null;
        try {
            Class<?> clazz = Class.forName("com.simon.java.annotation.NoBug");
            Constructor<?> constructor = clazz.getConstructor();
            object = constructor.newInstance();
            Method[] method = clazz.getDeclaredMethods();
            StringBuilder log = new StringBuilder();
            int errorTimes = 0;
            for (Method m : method) {
                if (m.isAnnotationPresent(TestAnnotation.class)) {
                    try {
                        m.setAccessible(true);
                        m.invoke(object);
                    } catch (Exception e) {
                        errorTimes++;
                        log.append(m.getName());
                        log.append(" ");
                        log.append("has error:");
                        log.append("\n\r");
                    }
                }
            }
            log.append(clazz.getSimpleName());
            log.append(" has  ");
            log.append(errorTimes);
            log.append(" error.");
            System.out.println(log.toString());
        } catch (InstantiationException | InvocationTargetException
                | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
