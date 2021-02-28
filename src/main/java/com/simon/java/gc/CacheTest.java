package com.simon.java.gc;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

/**
 * 使用 get 方法获取缓存的值。比如，当我们执行 lc.get("test") 时，第一次会比较缓慢，
 * 因为它需要到数据源进行获取；第二次就瞬间返回了，因为缓存命中了
 * <p>
 * 缓存一般是比较昂贵的组件，容量是有限制的，设置得过小，或者过大，都会影响缓存性能：
 * 缓存空间过小，就会造成高命中率的元素被频繁移出，失去了缓存的意义；
 * 缓存空间过大，不仅浪费宝贵的缓存资源，还会对垃圾回收产生一定的压力。
 */
public class CacheTest {
    public static void main(String[] args) {
        LoadingCache<String, String> lc = CacheBuilder
                .newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return slowMethod("test");
                    }
                });
        try {
            System.out.println(System.currentTimeMillis());
            System.out.println(lc.get("test") + "1:" + System.currentTimeMillis());
            System.out.println(System.currentTimeMillis());
            System.out.println(lc.get("test") + "2:" + System.currentTimeMillis());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static String slowMethod(String key) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return key + ".result";
    }


}
