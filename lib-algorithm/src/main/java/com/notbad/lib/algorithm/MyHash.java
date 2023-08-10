package com.notbad.lib.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyHash {
    class Pair{
        public int key;
        public String value;
        public Pair(int key,String value){
            this.key = key;
            this.value = value;
        }
    }
    private List<Pair> buckets;


    private int hashCal(int key){
        int index = key%100;
        return index;
    }

    public String get(int key){
        int index = hashCal(key);
        Pair p = buckets.get(index);
        if(p==null){
            return "null";
        }
        return p.value;
    }

    public void put(int key,String value) {
        Pair pair = new Pair(key,value);
        int index = hashCal(key);
        buckets.set(index,pair);
    }

    private void test(){
        buckets = new ArrayList<>(100);
        for (int i=0;i<100;i++){
            buckets.add(null);
        }

        put(22,"aa");
        put(33,"bb");
        put(44,"cc");
        put(66,"dd");
        put(88,"ee");
        LogUtils.d("66:"+get(66) );
    }


    public static void main(String[] args) {
        new MyHash().test();
    }


}
