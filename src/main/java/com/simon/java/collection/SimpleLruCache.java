package com.simon.java.collection;

import java.util.HashMap;

/**
 * @author Simon
 * Desc ：
 */
public class SimpleLruCache {

    /**
     * 对于某一个 key，我们可以通过哈希表快速定位到链表中的节点，从而取得对应 val。
     */
    private final HashMap<Integer, Node> queryMap;

    private final DoubleLink cacheLinkList;

    private int capacity;

    public SimpleLruCache(int capacity) {
        this.capacity = capacity;
        queryMap = new HashMap<>();
        cacheLinkList = new DoubleLink();
    }

    /**
     * 将已缓存的数据 移到链表尾部 提升为最近使用
     *
     * @param key
     */
    private void makeRecently(int key) {
        Node node = queryMap.get(key);
        cacheLinkList.remove(node);
        cacheLinkList.addLast(node);
    }

    /**
     * 添加 最新的数据 到最近使用
     *
     * @param key key
     * @param val value
     */
    private void addRecently(int key, int val) {
        Node newNode = new Node(key, val);
        cacheLinkList.addLast(newNode);
        queryMap.put(key, newNode);
    }

    /**
     * 将缓存的数据进行删除
     *
     * @param key 数据的键值
     */
    private void deleteKey(int key) {
        Node node = queryMap.get(key);
        cacheLinkList.remove(node);
        queryMap.remove(key);
    }

    /**
     * 删除缓存数据中 最久未使用的数据
     */
    private void deleteLastRecently() {
        Node node = cacheLinkList.removeFirst();
        queryMap.remove(node.key);
    }

    /**
     * -1 定义为不存在改数据
     *
     * @param key 数据的键值
     * @return
     */
    public int get(int key) {
        if (queryMap.containsKey(key)) {
            return queryMap.get(key).val;
        } else {
            return -1;
        }
    }

    /**
     * 分两种情况：
     * 一，若key存在，修改key对应的value 并将该数据提升为最近使用
     * 二，若key不存在，需要插入新的key值数据，插入过程中需要判断容量是否已满，
     * 未满：插入数据为最近使用的数据
     * 已满：删除最久未使用的数据在插入
     *
     * @param key   数据的键值
     * @param value 缓存的数据
     */

    public void put(int key, int value) {
        if (queryMap.containsKey(key)) {
            //删除旧数据
            deleteKey(key);
            //插入新的数据
            addRecently(key, value);
            return;
        }
        //容量已满
        if (capacity == cacheLinkList.getSize()) {
            //删除最久未使用的数据
            deleteLastRecently();
        }
        addRecently(key, value);
    }


    class Node {
        public int key, val;
        public Node prev, next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }


    /**
     * 使用链表 是为了支持在任意位置进行插入和删除
     */
    class DoubleLink {

        private final Node head;
        private final Node tail;
        private int size;

        /**
         * 数据存储使用双向链表，需要删除操作。删除一个节点不光要得到该节点本身的指针，也需要操作其前驱节点的指针，
         * 而双向链表才能支持直接查找前驱，保证操作的时间复杂度 O(1)。
         */
        public DoubleLink() {
            //初始化双向链表数据
            head = new Node(0, 0);
            tail = new Node(0, 1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        /**
         * 在链表尾部添加新的 数据  时间O(1)
         *
         * @param node 插入的数据
         */
        public void addLast(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
            size++;
        }

        /**
         * 删除 链表中的节点
         *
         * @param node remove node object
         */
        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        /**
         * 删除链表中的第一个节点 并返回该节点
         *
         * @return first node
         */
        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            } else {
                Node node = head.next;
                remove(node);
                return node;
            }
        }

        /**
         * 返回链表长度
         *
         * @return size value
         */
        public int getSize() {
            return size;
        }
    }

}
