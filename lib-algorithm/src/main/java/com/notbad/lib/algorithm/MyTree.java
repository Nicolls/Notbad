package com.notbad.lib.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class MyTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }


    private void test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
//        TreeNode insertNode = new TreeNode(6);
//        n1.left = insertNode;
//        insertNode.left = n2;
        List<Integer> dataList = levelOrder(n1);


        List<Integer> orderList = new ArrayList<>();
        preOrder(n1,orderList);
        LogUtils.d("s:" + orderList);
        List<Integer> stackorderList = new ArrayList<>();
        stackPreOrder(n1,stackorderList);
        LogUtils.d("s2:" + stackorderList);
    }

    /* 层序遍历 通过队列来实现*/
    private List<Integer> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 初始化列表，用于保存遍历的序列值
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            list.add(treeNode.val);
            // 把左加入
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            // 把右加入
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
            // 执行while，由于队列是一个先进先出的列表，所以最上面的左和右一定是先执行的
            // 如A下面挂了B和C，B下面挂了D和E,C下面挂了G和F。
            // 这时是A先入队，A出队后，把B和C入队，这时就到B出队，把D和E入队,因为C是在B的后面，这时再出队的就是C了，
            // 而不是刚入队的D，E。所以就能实现先遍历左再遍历右，再遍历同层的左和右，按层来遍历。
        }
        return list;
    }

    // 前序遍历，把主节点放在最开始添加。
    void preOrder(TreeNode root,List<Integer> dataList){
        if(root==null){
            return;
        }
        dataList.add(root.val); // 先把主节点加上
        preOrder(root.left,dataList); // 再加左节点，如果左节点还有子节点，则递归往下走
        preOrder(root.right,dataList); // 最后加右节点，如果右节点还有子节点，则递归往下走

    }
    // 中序遍历，把主节点放在中间添加
    void midOrder(TreeNode root,List<Integer> dataList){
        if(root==null){
            return;
        }
        midOrder(root.left,dataList); // 先加左节点，如果左节点还有子节点，则递归往下走
        dataList.add(root.val); // 再把主节点加上
        midOrder(root.right,dataList); // 最后加右节点，如果右节点还有子节点，则递归往下走

    }
    // 后序遍历，主节点在最后添加
    void rearOrder(TreeNode root,List<Integer> dataList){
        if(root==null){
            return;
        }
        rearOrder(root.left,dataList); // 先加左节点，如果左节点还有子节点，则递归往下走
        rearOrder(root.right,dataList); // 再加右节点，如果右节点还有子节点，则递归往下走
        dataList.add(root.val); // 最后把主节点加上
    }

    // 用栈来实现一个前序遍历
    void stackPreOrder(TreeNode root,List<Integer> dataList){
        Stack<TreeNode> stacks = new Stack<>();
        // 先加主节点，再加左，最后加右
        stacks.push(root);
        while (!stacks.isEmpty()){
            TreeNode treeNode = stacks.pop();
            dataList.add(treeNode.val);
            if(treeNode.right!=null){
                stacks.push(treeNode.right);
            }
            if(treeNode.left!=null){
                stacks.push(treeNode.left);
            }
        }
    }

    private void h(TreeNode left, TreeNode right, List<Integer> dataList) {
        if (left != null) {
            dataList.add(left.val);
        }
        if (right != null) {
            dataList.add(right.val);
        }
        if (left != null) {
            h(left.left, left.right, dataList);
        }
        if (right != null) {
            h(right.left, right.right, dataList);
        }
    }

    public static void main(String[] args) {
        MyTree myTree = new MyTree();
        myTree.test();
    }

}
