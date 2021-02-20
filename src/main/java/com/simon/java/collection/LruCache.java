package com.simon.java.collection;

import java.util.LinkedHashMap;

/**
 * @author Simon
 * Desc ：
 */
public class LruCache<K, V> {

    private int capacity;

    LinkedHashMap<K, V> cache;

    public LruCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must > 0");
        }
        this.capacity = maxSize;
        this.cache = new LinkedHashMap<K, V>(0, 0.75f, true);
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            makeRecently(key);
            return;
        }

        if (cache.size() >= capacity) {
            K oldestData = cache.keySet().iterator().next();
            cache.remove(oldestData);
        }
        cache.put(key, value);

    }

    private void makeRecently(K key) {
        V v = cache.get(key);
        cache.remove(key);
        cache.put(key, v);
    }
}
