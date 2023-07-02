package com.notbad.lib.algorithm;

import java.util.Arrays;

/**
 * 排序相关算法
 * <p>
 * 排序算法可以分为内部排序和外部排序，内部排序是数据记录在内存中进行排序，而外部排序是因排序的数据很大，
 * 一次不能容纳全部的排序记录，在排序过程中需要访问外存。常见的内部排序算法有：
 * 插入排序、希尔排序、选择排序、冒泡排序、归并排序、快速排序、堆排序、基数排序等
 * <p>
 * 关于时间复杂度：
 * 1 平方阶 (O(n2)) 排序 各类简单排序：直接插入、直接选择和冒泡排序。
 * 2 线性对数阶 (O(nlog2n)) 排序 快速排序、堆排序和归并排序。
 * 3 O(n1+§)) 排序，§ 是介于 0 和 1 之间的常数。希尔排序。
 * 4 线性阶 (O(n)) 排序 基数排序，此外还有桶、箱排序。
 * <p>
 * 关于稳定性：
 * 稳定的排序算法：冒泡排序、插入排序、归并排序和基数排序。
 * 不是稳定的排序算法：选择排序、快速排序、希尔排序、堆排序。
 * 名词解释：
 * n：数据规模
 * k：“桶”的个数
 * In-place：占用常数内存，不占用额外内存
 * Out-place：占用额外内存
 * 稳定性：排序后 2 个相等键值的顺序和排序之前它们的顺序相同
 */
public class SortSolution {
    /**
     * 冒泡排序
     * 冒泡排序（Bubble Sort）也是一种简单直观的排序算法。它重复地走访过要排序的数列，一次比较两个元素，
     * ----------
     * 每遍历一次，就对比当前和下一个，如果当前的比下一个大就交换它们的位置，这样一次遍历下来就一定能找到
     * 这个剩余位排列数组中最大的那一个。但因为一次只能找到一个最大值，所以需要遍历N次，让这本来N个空位上都
     * 填上最大的那个值。
     * 最佳情况：T(n) = O(n) 最差情况：T(n) = O(n2) 平均情况：T(n) = O(n2)
     * <p>
     * 原顺序:[12, 5, 60, 3, 6, 21, 18, 5, 8]
     * <p>
     * 第1次：[5, 12, 3, 6, 21, 18, 5, 8, 60]
     * 第2次：[5, 3, 6, 12, 18, 5, 8, 21, 60]
     * 第3次：[3, 5, 6, 12, 5, 8, 18, 21, 60]
     * 第4次：[3, 5, 6, 5, 8, 12, 18, 21, 60]
     * 第5次：[3, 5, 5, 6, 8, 12, 18, 21, 60]
     * 第6次：[3, 5, 5, 6, 8, 12, 18, 21, 60]
     * 第7次：[3, 5, 5, 6, 8, 12, 18, 21, 60]
     * 第8次：[3, 5, 5, 6, 8, 12, 18, 21, 60]
     * 第9次：[3, 5, 5, 6, 8, 12, 18, 21, 60]
     * <p>
     * 排后序:[3, 5, 5, 6, 8, 12, 18, 21, 60]
     */
    public void bubbleSort(int[] data) {
        int len = data.length;
        // 第一次只找到一个当前最大的，需要遍历len次，就可以把排序做完，所以需要外面这个循环
        for (int i = 0; i < len - 1; i++) {
            // 这个for才是真正的冒泡，每遍历一次，就把最大的那个放到最后面。
            // 遍历完一次后，又要重头开始去找下一个最大的。
            // 因此，每下一次遍历，它就不用遍历全部数组，因为最后那一个一定是比你这次要大的，没必要比较了。
            // 因为一次，只能把一个最大的放到最后面，所以我们仍然需要，len-1次for，那就相当于外面的for循环了
            for (int j = 0; j < len - i - 1; j++) { // 如果只有这一个for，只能找到这里面数最大的放在最后面，没有完成排序。
                int current = data[j];
                int next = data[j + 1];
                // 当前的跟下一个比较，如果当前比下一个大就跟下一个换一下位置。
                if (current > next) {
                    data[j + 1] = current;
                    data[j] = next;
                }
            }
            LogUtils.d("第" + (i + 1) + "次：" + Arrays.toString(data));
        }
    }

    /**
     * 选择排序
     * 选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。所以用到它的时候，数据规模越小越好。
     * 唯一的好处可能就是不占用额外的内存空间。
     * ----------
     * 可以想象，对于当前数组A，copy了一份数组A'，再把A'清空，然后第一次把A'[0]填写上A[0]，然后遍历A[i++]跟A'[0]对比，
     * 交换最小的那一个，这时A'[0]已占用了A[0]。
     * 第二次遍历，就从用A'[1]开始，填上A[1]，继续和A[1+i++]对比交换，这样不断重复，最后列表就有序了。
     * 当然这里面的A'其实就是A。
     * 最佳情况：T(n) = O(n2) 最差情况：T(n) = O(n2) 平均情况：T(n) = O(n2)
     *
     * <p>
     * 原顺序:[12, 5, 60, 3, 6, 21, 18, 5, 8]
     * <p>
     * 第1次：[3, 12, 60, 5, 6, 21, 18, 5, 8]
     * 第2次：[3, 5, 60, 12, 6, 21, 18, 5, 8]
     * 第3次：[3, 5, 5, 60, 12, 21, 18, 6, 8]
     * 第4次：[3, 5, 5, 6, 60, 21, 18, 12, 8]
     * 第5次：[3, 5, 5, 6, 8, 60, 21, 18, 12]
     * 第6次：[3, 5, 5, 6, 8, 12, 60, 21, 18]
     * 第7次：[3, 5, 5, 6, 8, 12, 18, 60, 21]
     * 第8次：[3, 5, 5, 6, 8, 12, 18, 21, 60]
     * 第9次：[3, 5, 5, 6, 8, 12, 18, 21, 60]
     * <p>
     * 排后序:[3, 5, 5, 6, 8, 12, 18, 21, 60]
     *
     * @param data
     */
    public void selectionSort(int[] data) {
        int len = data.length;
        for (int i = 0; i < len - 1; i++) {
            int min = data[i]; // 我们假设data[0]最小
            // 我们遍历数组，如果数组中有比它小的，我们就换上去，换上去后再和剩余的继续对比
            // 这样一趟下来，我们就找到了最小的那一个了,因为i的前面都是找好最小的值了的，所以我们没有必要跟i前的比较了
            // 直接从i+1比较
            for (int j = i + 1; j < len; j++) {
                int temp = data[j];
                if (temp < min) { // 找到了比它小的，换上，让它成为最小的
                    data[i] = temp;
                    data[j] = min;
                    min = temp; // 重新设置最小值
                }
                // 继续和剩余的对比
            }
            // 这次for遍历完成后，data[i]就是当前的最小值
            LogUtils.d("第" + (i + 1) + "次：" + Arrays.toString(data));
        }

    }

    /**
     * 插入排序
     * 插入排序是一种最简单直观的排序算法，它的工作原理是通过构建有序序列，
     * 对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * ----------
     * 插入排序其实就是方块移动，跟扑克牌一样，当我们抽出一张牌的时候，这个位置变空了，我们要插入到前3位去时，
     * 因为我们的数组长度是不能改变的，所以我们需要把跨过的这3张牌，每一张都往后移动一个位置去补位，
     * 然后再将我们抽出的牌插到移动后空出来的这个位置，
     * 这样就完成了插入的排序。
     * 最佳情况：T(n) = O(n) 最坏情况：T(n) = O(n2) 平均情况：T(n) = O(n2)
     * <p>
     * 原顺序:[12, 5, 60, 3, 6, 21, 18, 5, 8]
     * <p>
     * 第1次：[5, 12, 60, 3, 6, 21, 18, 5, 8]
     * 第2次：[5, 12, 60, 3, 6, 21, 18, 5, 8]
     * 第3次：[3, 5, 12, 60, 6, 21, 18, 5, 8]
     * 第4次：[3, 5, 6, 12, 60, 21, 18, 5, 8]
     * 第5次：[3, 5, 6, 12, 21, 60, 18, 5, 8]
     * 第6次：[3, 5, 6, 12, 18, 21, 60, 5, 8]
     * 第7次：[3, 5, 5, 6, 12, 18, 21, 60, 8]
     * 第8次：[3, 5, 5, 6, 8, 12, 18, 21, 60]
     * <p>
     * 排后序:[3, 5, 5, 6, 8, 12, 18, 21, 60]
     *
     * @param data
     */
    public void insertSort(int[] data) {
        int len = data.length;
        for (int i = 0; i < len - 1; i++) {
            // 我们默认第一个是排好的。所以我们从j=i+1个开始取出来向左边的数组进行插入
            // 因为我们要向左边插入，所以我们的范围是【j..0】，不断的和前一个比较，如果它比我们大，就跟它换位置，否则不就用换了。
            for (int j = i + 1; j > 0; j--) {
                if (data[j - 1] >= data[j]) {
                    int temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                } else { // 因为前面都是排好序了的，如果碰到一个比自己还小的，那就可停止了，因为前面的一定都比我们小
                    break;
                }
            }
            // 每一圏排下来，i往前的左边范围都是一个有序的。
            LogUtils.d("第" + (i + 1) + "次：" + Arrays.toString(data));
        }

    }

    /**
     * 快速排序
     * <p>
     * 从数列中挑出一个元素，称为 “基准”（pivot）;
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
     * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     * 递归的最底部情形，是数列的大小是零或一，也就是永远都已经被排序好了。
     * 虽然一直递归下去，但是这个算法总会退出，因为在每次的迭代（iteration）中，它至少会把一个元素摆到它最后的位置去。
     * ----------
     * 快排看起来其实就是一边冒泡，一边交换排序。它的本质是希望以基准让所有小的放左边，所有大的往右边，再以左右为新的排序数组
     * 做同样的计算，最终成序。
     * 还是因为数组是固定的不可变的，所以为了实现这个需求，我们仍然需要有空位来完成，
     * 取基准值，一般我们以数组的第一个数来作为基础值，也就是data[0]。
     * 按普通的快排思维就是先找到右边哪个比它小，跟基准值位置换，再从左边找到比基准值大的，跟右边空位换。
     * 这里我们直接从右往左找，找到一个比pivot小的，记录下来位置j
     * 再从左往右找，找到一个比pivot大的值，记录下来i
     * 我们再把i跟j的值调换一下，这样就可以了。
     * <p>
     * 最佳情况：T(n) = O(nlogn) 最差情况：T(n) = O(n2) 平均情况：T(n) = O(nlogn)
     */
    public void quickSort(int[] data) {
        realQuickSort(data, 0, data.length - 1);
    }

    private void realQuickSort(int[] data, int left, int right) {
        int i = left;
        int j = right;
        if (left > right) {
            return;
        }
        int pivot = data[left];
        while (i < j) {
            // 先从右往左找到一个比pivot小的
            while (i < j && data[j] >= pivot) {
                j--;
            }
            // 再从左往右找到一个比pivot大的
            while (i < j && data[i] <= pivot) {
                i++;
            }
            if (i < j) {
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        // 交换基准值
        data[left] = data[i];
        data[i] = pivot;
        //递归调用左半数组
        realQuickSort(data, left, j - 1);
        //递归调用右半数组
        realQuickSort(data, j + 1, right);
    }

    /**
     * 希尔排序
     * 希尔排序，也称递减增量排序算法，是插入排序的一种更高效的改进版本。但希尔排序是非稳定排序算法。
     * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
     * 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率；
     * 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位；
     * ----------
     */
    public void shellSort(int[] data) {
        int gap = 1;
        while (gap < data.length) {
            gap = gap * 3 + 1;
        }

        while (gap > 0) {
            for (int i = gap; i < data.length; i++) {
                int tmp = data[i];
                int j = i - gap;
                while (j >= 0 && data[j] > tmp) {
                    data[j + gap] = data[j];
                    j -= gap;
                }
                data[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3);
        }
    }

    public static void main(String[] args) {
        new SortSolution().test();
    }

    public void test() {
        int[] data = new int[]{12, 5, 60, 3, 6, 21, 18, 5, 8};
        LogUtils.d("原顺序:" + Arrays.toString(data));
        System.out.println("");
//        bubbleSort(data);
//        selectionSort(data);
//        insertSort(data);
//        quickSort(data);
        shellSort(data);
        System.out.println("");
        LogUtils.d("排后序:" + Arrays.toString(data));
    }

}
