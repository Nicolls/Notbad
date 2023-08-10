package com.notbad.lib.algorithm;

import java.util.Arrays;
import java.util.HashMap;

public class SearchSolution {
    // 二分查找，有序数组
    int binarySearch(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int m = i + (j - i) / 2; // j=i得到的是它们相隔的段，需要把i加上，才是它的索引
            int middle = nums[m];
            if (middle < target) {
                i = m + 1; // m肯定不是，那就往前+1
            } else if (middle > target) {
                j = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /* 二分查找插入点（无重复元素） */
    int binarySearchInsertionSimple(int[] nums, int target) {
        int i = 0, j = nums.length - 1; // 初始化双闭区间 [0, n-1]
        while (i <= j) {
            int m = i + (j - i) / 2; // 计算中点索引 m
            if (nums[m] < target) {
                i = m + 1; // target 在区间 [m+1, j] 中
            } else if (nums[m] > target) {
                j = m - 1; // target 在区间 [i, m-1] 中
            } else {
                return m; // 找到 target ，返回插入点 m
            }
        }
        // 未找到 target ，返回插入点 i
        return i;
    }


    /* 二分查找插入点（存在重复元素） */
    int binarySearchInsertion(int[] nums, int target) {
        int i = 0, j = nums.length - 1; // 初始化双闭区间 [0, n-1]
        while (i <= j) {
            int m = i + (j - i) / 2; // 计算中点索引 m
            if (nums[m] < target) {
                i = m + 1; // target 在区间 [m+1, j] 中
            } else if (nums[m] > target) {
                j = m - 1; // target 在区间 [i, m-1] 中
            } else {
                // 首个小于 target 的元素在区间 [i, m-1] 中
                // 是target值，又因为这是个有序的数组，所以我们再在[i, m-1]中，寻找第一个target值插入即可。
                j = m - 1;
            }
        }
        // 返回插入点 i
        return i;
    }

    // 查找两数之和，使用hasmap
    private int[] searchSumHashTable(int[] nums,int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            int temp = target-nums[i];
            if(map.containsKey(temp)){
                int index = map.get(temp);
                return new int[]{index,i};
            } else {
                map.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }


    public static void main(String[] args) {
        new SearchSolution().test();
    }

    public void test() {
        int[] nums = {2, 7, 15, 23, 46, 50, 51, 78, 86};
        int[] indexs = searchSumHashTable(nums,73);
        LogUtils.d("r:" + Arrays.toString(indexs));
    }


}
