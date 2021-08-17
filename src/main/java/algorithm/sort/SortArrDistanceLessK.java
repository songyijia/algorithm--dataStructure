package algorithm.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 快要排好序的数组里面，相邻元素移动少于k，选择合适策略对数组排序
 * 在固定的排序空间内，对整个数组进行排序===》小根堆！！
 */
public class SortArrDistanceLessK {
    public static void sortedArrDistanceLessK(int[] arr,int k){
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (;index <= Math.min(arr.length-1,k);index++){
            heap.add(arr[index]);
        }
        int i = 0;
        for (;index<arr.length;i++,index++){
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()){
            arr[i++] = heap.poll();
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,1,4,3,6,5,9};
        sortedArrDistanceLessK(arr,3);
        System.out.println(Arrays.toString(arr));
    }
}
