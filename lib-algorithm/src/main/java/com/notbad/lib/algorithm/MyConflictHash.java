package com.notbad.lib.algorithm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListSelectionModel;

public class MyConflictHash {
    class Pair{
        public int key;
        public String value;
        public Pair(int key,String value){
            this.key = key;
            this.value = value;
        }
    }
    public class HashMapCh{
        int size;
        int capacity;
        double loadThres;
        int extendRatio;
        List<List<Pair>> buckets;
        public HashMapCh(){
            size = 0;
            capacity = 4;
            loadThres = 2/3.0;
            extendRatio = 2;
            buckets = new ArrayList<>();
            for (int i=0;i<capacity;i++){
                buckets.add(new ArrayList<Pair>());
            }
        }

        public int hashFunc(int key){
            return key%capacity;
        }

        double loadFactor(){
            return (double) size/capacity;
        }

        String get(int key){
            int index = hashFunc(key);
            List<Pair> bucket = buckets.get(index);
            for (Pair pair:bucket){
                if(pair.key==key){
                    return pair.value;
                }
            }
            return "null";
        }
        void put(int key,String value){
            // 当负载因子超过阈值，则扩容
            if(loadFactor()>loadThres){
                extend();
            }
            int index = hashFunc(key);
            List<Pair> list = buckets.get(index);
            for (Pair pair:list){
                if(pair.key == key){
                    pair.value = value;
                    return;
                }
            }
            Pair pair = new Pair(key,value);
            list.add(pair);
            size++;
        }

        void remove(int key){
            int index = hashFunc(key);
            List<Pair> list = buckets.get(index);
            for (Pair pair:list){
                if(pair.key == key){
                    list.remove(pair);
                    size--;
                    break;
                }
            }
        }

        void extend(){
            List<List<Pair>> temp = buckets;
            capacity*=extendRatio;
            buckets = new ArrayList<>(capacity);
            for (int i=0;i<capacity;i++) {
                buckets.add(new ArrayList<Pair>());
            }
            size = 0;
            for (List<Pair> bucket:temp){
                for (Pair pair:bucket){
                    put(pair.key,pair.value);
                }
            }
        }

        void print(){
            for (List<Pair> bucket:buckets){
                List<String> keys = new ArrayList<>();
                for (Pair pair:bucket){
                    keys.add(pair.value);
                }
                LogUtils.d("bucket:" + keys);
            }
        }

    }

    public void test(){
        HashMapCh mapCh = new HashMapCh();
        mapCh.put(11,"aa");
        mapCh.put(22,"bb");
        mapCh.put(30,"cc");
        mapCh.put(40,"dd");
        mapCh.put(52,"ee");
        mapCh.put(60,"ff");
        mapCh.put(70,"gg");
        mapCh.put(80,"xx");
        mapCh.put(98,"rr");
        mapCh.put(100,"zz");

        mapCh.print();
    }

    public static void main(String[] args){
        new MyConflictHash().test();
    }
}
