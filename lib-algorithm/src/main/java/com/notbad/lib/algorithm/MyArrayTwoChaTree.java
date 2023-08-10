package com.notbad.lib.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MyArrayTwoChaTree {

    public class ArrayBinaryTree{
        private List<Integer> tree;
        public ArrayBinaryTree(List<Integer> tree){
            this.tree = new ArrayList<>(tree);
        }
        public int size(){
            return tree.size();
        }

        public Integer get(int index){
            if(index<0||index>=tree.size()){
                return null;
            }
            return tree.get(index);
        }
        // 节点对应的左子节点索引
        public int leftIndex(int i){
            return 2*i+1;
        }
        // 节点对应的右子节点索引
        public int rightIndex(int i){
            return 2*i+2;
        }
        // 节点对应的父节点索引
        public int parentIndex(int i){
            return (i-1)/2;
        }
        // 层序遍历
        public List<Integer> levelOrder(){
            List<Integer> res = new ArrayList<>();
            for(int i=0;i<size();i++){
                if(get(i)!=null){
                    res.add(get(i));
                }
            }
            return res;
        }

        private void dfs(int i,List<Integer> dataList){
            if(get(i)==null){
                return;
            }
            // 前序遍历
            dataList.add(get(i));
            dfs(leftIndex(i),dataList);
            // dataList.add(get(i)); // 中序遍历
            dfs(rightIndex(i),dataList);
            // dataList.add(get(i)); // 后序遍历
        }
    }

    private void test(){

    }

    public static void main(String[] args){
        new MyArrayTwoChaTree().test();
    }
}
