package com.simon.java.lrucache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LrucacheTest {
    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<Integer, Integer>(2);
        linkedHashMap.put(1, 1);
        linkedHashMap.put(2, 2);
        linkedHashMap.put(3, 3);
        for (Map.Entry<Integer,Integer> a : linkedHashMap.entrySet()) {
            System.out.println("key->" + a.getKey() + "");
            System.out.println( "value->" + a.getValue() + "");
        }
    }
}
