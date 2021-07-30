package com.simon.java.leetcode;

public class LeetCode2 {


    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        ListNode listNode1 = new ListNode(5);
        ListNode listNode2 = new ListNode(6);
        ListNode listNode3 = new ListNode(9);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(8);

        listNode.next = listNode1;
        listNode1.next = listNode2;

        listNode3.next = listNode4;
        listNode4.next = listNode5;


    }

    public static ListNode sum(ListNode listNode, ListNode otherListNode) {
        // TODO: 2019/9/18 第一步 反转链表   第二步 比较链表长度   第三步 遍历链表（遍历链表较长的那个） 由低位同位相加 超过10 temp记录 加入下一位计算 添加到链表中 直至遍历结束  第四步 再次反转结果链表返回


        return null;
    }


    //Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


}