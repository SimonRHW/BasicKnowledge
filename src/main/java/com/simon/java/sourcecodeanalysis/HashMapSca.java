package com.simon.java.sourcecodeanalysis;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 在 JDK 1.7 中 HashMap 是以数组加链表的形式组成的，
 * JDK 1.8 之后新增了红黑树的组成结构，当链表大于 8 并且容量大于 64 时，
 * 链表结构会转换成红黑树结构,之所以添加红黑树是因为一旦链表过长，会严重影响 HashMap 的性能，
 * 而红黑树具有快速增删改查的特点，这样就可以有效的解决链表过长时操作比较慢的问题
 * HashMap三个比较重要的方法：查询、新增、扩容
 */
public class HashMapSca {

    public static void main(String[] args) {

        var hashMap = new HashMap<String, String>();

        /*
        HashMap 是如何导致死循环的？
        以 JDK 1.7 为例，假设 HashMap 默认大小为 2，原本 HashMap 中有一个元素 key(5)，我们再使用两个线程：t1 添加元素 key(3)，
        t2 添加元素 key(7)，当元素 key(3) 和 key(7) 都添加到 HashMap 中之后，
        线程 t1 在执行到 Entry<K,V> next = e.next; 时，交出了 CPU 的使用权
        那么此时线程 t1 中的 e 指向了 key(3)，而 next 指向了 key(7) ；
        之后线程 t2 重新 rehash 之后链表的顺序被反转，链表的位置变成了 key(5) → key(7) → key(3)，
        其中 “→” 用来表示下一个元素。当 t1 重新获得执行权之后，先执行 newTalbe[i] = e 把 key(3) 的 next 设置为 key(7)，
        而下次循环时查询到 key(7) 的 next 元素为 key(3)，于是就形成了 key(3) 和 key(7) 的循环引用，因此就导致了死循环的发生

        当然发生死循环的原因是 JDK 1.7 链表插入方式为首部倒序插入，这个问题在 JDK 1.8 得到了改善，变成了尾部正序插入；
        因为 HashMap 本身就是非线程安全的，如果要在多线程下，建议使用 ConcurrentHashMap 替代

        void transfer(Entry[] newTable, boolean rehash) {
        int newCapacity = newTable.length;
        for (Entry<K,V> e : table) {
            while(null != e) {
                Entry<K,V> next = e.next; // 线程一执行此处
                if (rehash) {
                    e.hash = null == e.key ? 0 : hash(e.key);
                }
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
            }
        }
        */


    }


    /**
     * 哈希桶 每个桶包含四个字段 hash、key、value、next 表示链表的下一个节点
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> implements Map.Entry<K, V> {

        final int hash;
        final K key;
        V value;
        HashMapSca.Node<K, V> next;

        Node(int hash, K key, V value, HashMapSca.Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

}
