package com.notbad.lib.algorithm;

import java.util.Arrays;

public class MyArrays {


    // 向数组中添加一个数
    private int[] insertNum(int[] nums,int index, int num){
        for (int i=nums.length-1;i>index;i--) {
            nums[i]=nums[i-1];
        }
        nums[index]=num;
        return nums;
    }

    // 数组中删除一个数
    private int[] deleteNum(int[] nums,int index){
        for (int i=index;i<nums.length-1;i++){
            nums[i]=nums[i+1];
        }
        return nums;
    }

    public void test(){
        int[] data = {12,5,7,30,3,27,8};
        LogUtils.d("befor:" + Arrays.toString(data));
//        LogUtils.d("after:" + Arrays.toString(insertNum(data,3,6)));
        LogUtils.d("after:" + Arrays.toString(deleteNum(data,3)));
    }

    public static void main(String[] args) {
        new MyArrays().test();
    }

}
