package com.simon.java.algorithm;

import com.simon.java.bean.TreeNode;

import java.util.ArrayDeque;

/**
 *
 */
public class SearchAlgorithm {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        one.setLeftNode(two);
        one.setRightNode(three);
        two.setRightNode(five);
        two.setLeftNode(four);
        four.setRightNode(eight);
        three.setLeftNode(six);
        three.setRightNode(seven);
        depthOrderTraversal(one);
        levelOrderTraversal(one);
    }

    /**
     * 深度优先遍历，相当于先根遍历
     * 采用非递归方式实现
     * 需要用数据结构栈来辅助
     * Depth-First-Search 深度优先搜索
     * step1：选择一个初始节点
     * step2：从这个节点开始搜索，同时标记已经搜索过的·点
     * step3：如果已经位于节点分支的尽头，则回到上一个分支节点处，重复步骤1
     * step4：搜索到目标
     *
     * @param root
     */
    public static void depthOrderTraversal(TreeNode root) {
        System.out.println("depthOrderTraversal start");
        if (root == null) {
            System.out.println("树为空，终止遍历");
            return;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node);
            if (node.getRightNode() != null) {
                stack.push(node.getRightNode());
            }
            if (node.getLeftNode() != null) {
                stack.push(node.getLeftNode());
            }
        }
        System.out.println("depthOrderTraversal end");
    }


    public static void levelOrderTraversal(TreeNode root) {
        System.out.println("levelOrderTraversal start");
        if (null == root) {
            System.out.println("树为空，终止遍历");
            return;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            System.out.println(node);
            if (node.getLeftNode() != null) {
                queue.add(node.getLeftNode());
            }
            if (node.getRightNode() != null) {
                queue.add(node.getRightNode());
            }
        }
        System.out.println("levelOrderTraversal end");
    }


}
