package com.notbad.lib.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MySort {

    // 选择排序
    // 每次从剩下的未排序的数据中选出一个最小的放到当前第i个要排序的位置中。
    private int[] selectSort(int[] nums) {
        // i < nums.length-1 是说最后一个不需要匹配了，因为下面的j会比较
        for (int i = 0; i < nums.length - 1; i++) {
            int currentMin = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int compareVal = nums[j];
                if (compareVal < currentMin) {
                    // 找到更小的，那就换一下位置
                    nums[i] = compareVal;
                    nums[j] = currentMin;
                    currentMin = compareVal; // 更新最小值
                }
            }
        }
        return nums;
    }

    // 冒泡排序
    // 就是每一轮将最大的值冒到数组的最后面，那第i次的时候，后面就有i个已经排好序的，
    // 这个跟选择排序结果像，只是选择排序是把排好序的从头开始放，而冒泡是把排好序的往后放
    private int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 因为我们每排一次，就可以把一个最大值放在最后面，所以j<nums.length-i
            // 又因为我们下面的交换是向后一个比较的，所以是j < nums.length-i-1
            for (int j = 0; j < nums.length - i - 1; j++) {
                // 我们每次的交换都是前一个跟后一个进行交换来完成的，
                // 并且这个交换会一直到length-i，不会因为其他情况停止
                if (nums[j + 1] > nums[j]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    // 插入排序
    // 我们在未排序区间选择一个基准元素，将该元素与其左侧已排序区间的元素逐一比较大小，并将该元素插入到正确的位置。
    private int[] insertSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            // 每次都从当前位置开始向0位置（已排好序的）找到合适的位置并插入
            // 这里我们取当前排好序的后一个位置来开始进行，这个相当于把基准位置拿出来
            int num = nums[i + 1];
            int j = i;
            while (j >= 0) {
                // 不停的往前找，如果比基准值大，那么就让它后移，
                // 腾出位置来放基准值，如果下一个还是比基准值大，继续后移
                if (nums[j] > num) {
                    nums[j + 1] = nums[j];
                    j--; // 往前找
                } else {
                    break;
                }
            }
            // 如果j没有发生过交换，那就还是原来的值
            // 如果j发生过交换，那因为有j--，所以最后需要j+1来指回交换的那个位置
            nums[j + 1] = num;
        }
        return nums;
    }

    // 快速排序
    public int[] quickSort(int[] data) {
        realQuickSort(data, 0, data.length - 1);
        return data;
    }

    private void realQuickSort(int[] data, int left, int right) {
        int i = left;
        int j = right;
        if (i >= j) {
            return;
        }
        int pivot = data[left];
        LogUtils.d("start while left:" + pivot + "-right:" + data[right]);
        while (i < j) {
            // 先从右边往左找到第一个小于pivot的值
            while (i < j && data[j] >= pivot) {
                j--;
            }
            // 再从左往右找到第一个大于pivot的值
            while (i < j && data[i] <= pivot) {
                i++;
            }
            // 交换它们
            if (i < j) {
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
            // 继续，这样我们就可以把这个区间内的数都排好序了
            LogUtils.d("while:" + Arrays.toString(data));
        }
        // 最终一定是i==j，而这个值就是一个比pivot还小的值
        LogUtils.d("changed pivot i=" + i + " j=" + j);
        // 交换基准值，上面中i一定是一个小于pivot的值，所以我们把它们交换，这样最小的还是在了最左边
        data[left] = data[i];
        data[i] = pivot;
        LogUtils.d("changed pivot ok:" + Arrays.toString(data));
        // 递归左半边的数组
        realQuickSort(data, left, i - 1);
        // 递归右半边的数组
        realQuickSort(data, i + 1, right);

    }

    // 归并排序
    private int[] mergeSort(int[] nums) {
        realMergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void realMergeSort(int[] nums, int left, int right) {
        // 终止条件
        if (left >= right) {
            return;
        }
        // 划分阶段
        int mid = (left + right) / 2; // 计算中点
        realMergeSort(nums, left, mid); // 左
        realMergeSort(nums, mid + 1, right); // 右
        // 合并阶段
        merge(nums, left, mid, right);

    }

    // 合并左子数组和右子数组,左右子数组都是排好序了的
    // 合并的逻辑就是从左右两个数据从头一起遍历，每次将小的那个放到nums中
    private void merge(int[] nums, int left, int mid, int right) {
        // 初始化辅助数组
        int[] tmp = Arrays.copyOfRange(nums, left, right + 1);
        // 在tmp中左子数组的起始和结束索引
        int leftStart = 0;
        int leftEnd = mid - left;
        // 在tmp中右子数组的起始和结束索引
        int rightStart = mid - left + 1;
        int rightEnd = right - left;
        int i = leftStart;
        int j = rightStart;
        // 通过覆盖原数组nums来合并，左右子数组，左右子数组分别已排好序
        // 逻辑是不断的从左和右数组取比较出来的最小值，来覆盖原数组值
        // 哪个数组被消耗完，则直接用另一个数组按序填充即可
        for (int k = left; k <= right; k++) {
            // 左数组被消耗完了，直接用右数据按序填充即可
            if (i > leftEnd) {
                nums[k] = tmp[j++];
            } else if (j > rightEnd) {
                // 右数组被消耗完了，直接用左数据按序填充即可
                nums[k] = tmp[i++];
            } else {
                // 两个数组都还有数据，则哪个小就填哪个
                if (tmp[i] <= tmp[j]) {
                    nums[k] = tmp[i++];
                } else {
                    nums[k] = tmp[j++];
                }
            }
        }
    }

    /* 堆排序 */
    void heapSort(int[] nums) {
        // 建堆操作：堆化除叶节点以外的其他所有节点
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            siftDown(nums, nums.length, i);
        }
        // 从堆中提取最大元素，循环 n-1 轮
        for (int i = nums.length - 1; i > 0; i--) {
            // 交换根节点与最右叶节点（即交换首元素与尾元素）
            int tmp = nums[0];
            nums[0] = nums[i];
            nums[i] = tmp;
            // 以根节点为起点，从顶至底进行堆化
            siftDown(nums, i, 0);
        }
    }

    /* 堆的长度为 n ，从节点 i 开始，从顶至底堆化 */
    void siftDown(int[] nums, int n, int i) {
        while (true) {
            // 判断节点 i, l, r 中值最大的节点，记为 ma
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            int ma = i;
            if (l < n && nums[l] > nums[ma])
                ma = l;
            if (r < n && nums[r] > nums[ma])
                ma = r;
            // 若节点 i 最大或索引 l, r 越界，则无需继续堆化，跳出
            if (ma == i)
                break;
            // 交换两节点
            int temp = nums[i];
            nums[i] = nums[ma];
            nums[ma] = temp;
            // 循环向下堆化
            i = ma;
        }
    }

    /* 桶排序 */
    void bucketSort(float[] nums) {
        // 初始化 k = n/2 个桶，预期向每个桶分配 2 个元素
        int k = nums.length / 2;
        List<List<Float>> buckets = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            buckets.add(new ArrayList<Float>());
        }
        // 1. 将数组元素分配到各个桶中
        for (float num : nums) {
            // 输入数据范围 [0, 1)，使用 num * k 映射到索引范围 [0, k-1]
            int i = (int) (num * k);
            // 将 num 添加进桶 i
            buckets.get(i).add(num);
        }
        // 2. 对各个桶执行排序
        for (List<Float> bucket : buckets) {
            // 使用内置排序函数，也可以替换成其他排序算法
            Collections.sort(bucket);
        }
        // 3. 遍历桶合并结果
        int i = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket) {
                nums[i++] = num;
            }
        }
    }

    // 汉诺塔算法
    private void solveHanota(List<Integer> src, List<Integer> buf, List<Integer> target) {
        int srcSize = src.size();
        // src列表是：9,8,7..,2,1 ，第一个值是最大的盘子，最后一个数字是最小的盘子。
        hanoiTower(srcSize, src, buf, target);
    }

    // 汉诺塔的整体逻辑是，在所有的移动中，都存在这样的最后的规律操作，
    // 最后src源柱上只剩下最后一个最大的盘子,而缓存柱buf上按顺序排着n-1的盘子，目标柱target上是空
    // 这时，需要把src上的最后盘子移到target上
    // 再把buf上的所有盘子移到target上。
    private void hanoiTower(int srcSize, List<Integer> src, List<Integer> buf, List<Integer> target) {
        if (srcSize == 1) {
            // 这个是因为，我们第一步中，会将源柱上的n-1个盘子移到缓存柱上，它会递归的让n-1的往缓存柱上挪动
            // 那么到达的条件就是最后那个盘子，也就是最上面那个盘子
            // 当递归到最上面那个盘子后就执行，把它从源柱上移到缓存柱上，因为递归的时候传入的target就是buf缓存柱
            // 所以我们只要把盘子从src移到target上即可完成源柱到缓存柱上的盘子移动
            Integer pan = src.remove(src.size() - 1);
            target.add(pan);
        } else {
            // 第一步，将源柱上的n-1往上的盘子挪到缓存柱上，因为方法中第一个参数是源柱，第二个是缓存柱，第三个是目标柱
            // 所以我们要把缓存柱传给第三个参数，这样盘就可以移到缓存柱上了
            hanoiTower(srcSize - 1, src, target, buf);
            // 第二步，当n-1的盘子都移到缓存柱上后，把第n个盘子移动到目标柱上
            Integer pan = src.remove(src.size() - 1);
            target.add(pan);
            // 第三步，当第n个盘子移动到目标柱上后，我们就可以把缓存柱上的盘子移到目标柱上了
            // 同理，我们需要把缓存柱传给第一个参数，这样就可以把缓存柱上的盘子往目标柱上移
            hanoiTower(srcSize - 1, buf, src, target);
        }

    }

    private void test() {
//        int[] nums = new int[]{12, 5, 60, 3, 6, 21, 18, 7, 9, 19, 25};
//        LogUtils.d("before:" + Arrays.toString(nums));
//        int[] result = mergeSort(nums);
//        LogUtils.d("after:" + Arrays.toString(result));
        List<Integer> a = new ArrayList<>();
        a.add(9);
        a.add(8);
        a.add(7);
        a.add(6);
        a.add(5);
        a.add(4);
        a.add(3);
        a.add(2);
        a.add(1);
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        solveHanota(a, b, c);
        LogUtils.d("a:" + a.toString());
        LogUtils.d("b:" + b.toString());
        LogUtils.d("c:" + c.toString());
    }

    public static void main(String[] args) {
        new MySort().test();
    }
}
