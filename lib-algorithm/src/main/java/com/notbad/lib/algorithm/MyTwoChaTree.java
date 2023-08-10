package com.notbad.lib.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MyTwoChaTree {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private void test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);
        TreeNode n11 = new TreeNode(11);
        TreeNode n12 = new TreeNode(12);
        TreeNode n13 = new TreeNode(13);
        TreeNode n14 = new TreeNode(14);
        TreeNode n15 = new TreeNode(15);
        TreeNode root = n8;
        n8.left = n4;
        n8.right = n12;
        n4.left = n2;
        n4.right = n6;
        n2.left = n1;
        n2.right = n3;
        n6.left = n5;
        n6.right = n7;
        n12.left = n10;
        n12.right = n14;
        n10.left = n9;
        n10.right = n11;
        n14.left = n13;
        n14.right = n15;
        remove(root, 6);
        List<Integer> dataList = new ArrayList<>();
        midOrder(root, dataList);
        LogUtils.d("result:" + dataList);
    }

    private void search(TreeNode root, int target) {
        // 因为我们这个树是一个二叉搜索树，它的左子树一定比右子数小，满足二分查找
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < target) { // 如果目标值比当前大，那么在它的右树查找
                cur = cur.right;
            } else if (cur.val > target) { // 如果目标值比当前小，那么在它的左树查找
                cur = cur.left;
            } else { // cur.val ==  target
                LogUtils.d("find target " + cur.val);
                break;
            }
        }
    }

    private void insert(TreeNode root, int target) {
        TreeNode cur = root;
        // 用来保存最后要插入的那个叶子结点
        // 最终我们要插入的节点是挂在叶子节点下的
        TreeNode pre = root;
        while (cur != null) {
            pre = cur;
            if (cur.val < target) {
                cur = cur.right;
            } else if (cur.val > target) {
                cur = cur.left;
            } else {
                // 如果找到相同的数，就不再执行插入
                LogUtils.d("exist target:" + target);
                return;
            }
        }
        // 到叶子节点了，且没有相同的数，根据值判断要插入左还是右
        TreeNode newNode = new TreeNode(target);
        if (pre.val < target) {
            pre.right = newNode;
        } else {
            pre.left = newNode;
        }
    }

    // 二叉搜索树，删除一个节点
    private void remove(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            // 找到当前节点
            if (cur.val == target) {
                break;
            }
            // 记录上一个节点
            pre = cur;
            if (cur.val < target) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        if (cur == null) {
            return;
        }
        // 如果子节点是0，则直接删除即可，如果子节点是1，直接把子节点替换成当前节点即可
        if (cur.left == null || cur.right == null) { // 子节点是0或者1
            TreeNode t = cur.left == null ? cur.right : cur.left;
            if (pre.left == cur) {
                pre.left = t;
            }
            if (pre.right == cur) {
                pre.right = t;
            }
        } else {
            // 当待删除节点的度为2时，我们无法直接删除它，而需要使用一个节点替换该节点。
            // 由于要保持二叉搜索树“左<根<右”的性质，因此这个节点可以是左子树的最大值节点，或者右子树的最小值节点来替换
            // 我们下面的方法是按从右子树的最小节点值来替换
            TreeNode curLeftNode = cur.left;
            TreeNode curRightNode = cur.right;
            TreeNode temp = curRightNode; // 先把当前节点指向右子树
            // 因为我们要找的是右子树的最小节点值，那从二叉搜索树的关系中，我们知道中序遍历是升序的，它会先找到树中的最小值
            // 所以这里我们只需要不停的去找它的左节点，直接叶子节点，那么这个节点就是最小值了。
            TreeNode rightTreePre = cur; // temp的父节点从当前节点开始
            while (temp.left != null) {
                rightTreePre = temp;
                temp = temp.left;
            }
            LogUtils.d("right tree smallest:" + temp.val);
            // 找到最小值后，先与之前的节点断开关系，因为它就要被用来填写被删除的节点
            rightTreePre.left = null;
            // 再用当前最小值节点，替换删除节点
            // 原被删除节点的上一级指向它
            if (pre.left == cur) {
                pre.left = temp;
            }
            if (pre.right == cur) {
                pre.right = temp;
            }
            // 它的左节点指向原来删除节点的左节点
            temp.left = curLeftNode;
            // 它的右节点，指向原来删除节点的右节点，如果右子树只有一个值，那它就是替换的值，就不需要再给值了
            if (temp != curRightNode) {
                temp.right = curRightNode;
            }
        }

    }

    private void midOrder(TreeNode root, List<Integer> dataList) {
        if (root.left != null) {
            midOrder(root.left, dataList);
        }
        dataList.add(root.val);
        if (root.right != null) {
            midOrder(root.right, dataList);
        }
    }


    public static void main(String[] args) {
        new MyTwoChaTree().test();
    }
}
