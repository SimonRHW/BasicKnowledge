package com.simon.java.collection;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CollectionTest {

    public static void main(String[] args) {
        List emptyList = Collections.EMPTY_LIST;
        testLinkHashMap();
    }

    public static void testLinkHashMap() {

        LinkedHashMap<String, String> accessOrderedMap = new LinkedHashMap<String, String>(16, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 3;
            }
        };

        accessOrderedMap.put("1", "haha");
        accessOrderedMap.put("2", "hehe");
        accessOrderedMap.put("3", "heihei");

        accessOrderedMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);

        });
        accessOrderedMap.get("2");
        accessOrderedMap.get("2");
        accessOrderedMap.get("3");

        System.out.println("-----");
        accessOrderedMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);

        });
        accessOrderedMap.put("4", "hihi");

        System.out.println("-----");
        accessOrderedMap.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });


    }
}
