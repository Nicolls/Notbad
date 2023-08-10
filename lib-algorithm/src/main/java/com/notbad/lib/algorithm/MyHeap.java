package com.notbad.lib.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyHeap {
    // 堆
    public class Heap {
        private List<Integer> dataList = new ArrayList<>();

        public int size() {
            return dataList.size();
        }

        public Integer get(int i) {
            if (i >= 0 && i < dataList.size()) {
                return dataList.get(i);
            }
            return null;
        }

        public int leftIndex(int i) {
            return 2 * i + 1;
        }

        public int rightIndex(int i) {
            return 2 * i + 2;
        }

        public int parentIndex(int i) {
            return (i - 1) / 2;
        }

        public List<Integer> levelOrder() {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size(); i++) {
                list.add(get(i));
            }
            return list;
        }

        public void order(int index, List<Integer> dataList) {
            Integer d = get(index);
            if (d == null) {
                return;
            }
            // 后序遍历
            order(leftIndex(index), dataList);
            order(rightIndex(index), dataList);
            dataList.add(d);
        }

        // 入堆, 因为我们是用数组来实现的堆，
        // 所以我们最合适的当然是将要插入的数据直接添加到数组最后面,从底至顶执行堆化.
        // 但可能添加的数据不符合堆的条件，这时又需要对添加的数据进行修正.
        // 我们比较插入节点与其父节点的值，如果插入节点更大，则将它们交换。
        // 然后继续执行此操作，从底至顶修复堆中的各个节点，直至越过根节点或遇到无需交换的节点时结束
        public void push(int data) {
            dataList.add(data);
            int currentIndex = dataList.size() - 1; // 插入的点，这时它一定是堆的最底部
            // 下面我们开始修正
            while (currentIndex >= 0) {
                int current = dataList.get(currentIndex);
                int parentIndex = parentIndex(currentIndex);
                int parentVal = get(parentIndex);
                if (current > parentVal) {
                    dataList.set(parentIndex, current);
                    dataList.set(currentIndex, parentVal);
                    currentIndex = parentIndex;
                } else {
                    return;
                }
            }
        }

        public int poll() {
            /**
             * 交换堆顶元素与堆底元素（即交换根节点与最右叶节点）。
             * 交换完成后，将堆底从列表中删除（注意，由于已经交换，实际上删除的是原来的堆顶元素）。
             * 从根节点开始，从顶至底执行堆化。
             */
            // 交换顶和底，就是把顶的值设置成底的值，然后把底删除掉就可以了
            int top = get(0);
            dataList.set(0, get(size() - 1));
            dataList.remove(size() - 1);
            // 从根节点往下堆化
            int currentIndex = 0;
            while (currentIndex < size()) { // 最多也就是size了
                // 取得当前节点的左右节点位置
                int leftIndex = leftIndex(currentIndex);
                int rightIndex = rightIndex(currentIndex);
                // 取得当前节点的左右节点值
                Integer current = get(currentIndex);
                Integer left = get(leftIndex);
                Integer right = get(rightIndex);
                if (current == null) {
                    break;
                }
                // 如果当前节点比它的左节点还小，那就跟它交换
                if (left != null && current < left) {
                    dataList.set(currentIndex, left);
                    dataList.set(leftIndex, current);
                    currentIndex = leftIndex;

                } else if (right != null && current < right) {
                    // 否则如果当前节点比它的右节点还小，那就跟它交换
                    dataList.set(currentIndex, right);
                    dataList.set(rightIndex, current);
                    currentIndex = rightIndex;
                } else {
                    // 这里说明它比它的左和右节点都要大，符合堆条件，不用再更换了
                    break;
                }

            }
            return top;
        }

        // 建堆
        public void build(List<Integer> list) {
            dataList = new ArrayList<>(list);
            for (int i = size() - 1; i >= 0; i--) { // 一个个进行堆化
                Integer current = get(i);
                int parentIndex = parentIndex(i);
                Integer parent = get(parentIndex);
                if (current == null || parent == null) {
                    break;
                }
                if (current > parent) {
                    dataList.set(i, parent);
                    dataList.set(parentIndex, current);
                }
            }
        }

        // 访问堆顶元素
        public int peek() {
            return get(0);
        }


    }

    // 获取该列表中前k个最大值
    private Queue<Integer> topK(List<Integer> list, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < list.size(); i++) {
            int d = list.get(i);
            // 我们先把前k个入堆，切记，入堆的操作会自动伴随着堆化
            // 默认它遵循着小顶堆的规则，也就是入完k个数后，第0个一定是k列表里最小的那个数。
            if (i < k) {
                queue.offer(d);
            } else {
                // 因为我们的优先队列默认是小顶堆，所以第0个就是最小的值，只需要每次比较入队的值是否比当前最小的值大
                // 如果是，我们就入堆，否则不入堆
                if (d > queue.peek()) {
                    queue.poll(); // 为了保证我们的队列只留有k个数，我们先把最小的那一个给出堆
                    queue.offer(d); // 再把这个入堆，入堆后也会自动堆化，让第0个仍然是当前K个里面最小的
                }
            }
        }
        return queue;
    }

    private void test() {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(3);
        list.add(26);
        list.add(123);
        list.add(7);
        list.add(11);
        list.add(9);
        list.add(23);
        Queue<Integer> topList = topK(list,3);
        while (!topList.isEmpty()){
            LogUtils.d("topK:" + topList.poll());
        }
        Heap heap = new Heap();
        // 第一种建堆方法，通过入堆函数实现

//        for (Integer d:list){
//            heap.push(d);
//        }
        // 第二种建堆方法，直接创建数据后，再作堆化
        heap.build(list);
        LogUtils.d("level:" + heap.levelOrder());
        LogUtils.d("max:" + heap.poll());
        LogUtils.d("poll:" + heap.levelOrder());
    }

    public static void main(String[] args) {
        new MyHeap().test();
    }
}
