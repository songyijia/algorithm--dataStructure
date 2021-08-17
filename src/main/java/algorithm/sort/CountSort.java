package algorithm.sort;

import java.util.Arrays;

/**
 * 桶排序：不基于比较的排序：计数排序 和 基数排序
 * 计数排序：样本数有限：年龄。。
 * 时间复杂度：O(n)
 * 基数排序：非负数，且10进制，分别以个，十，百位排序，入桶，出桶
 */
public class CountSort {
    public static void countSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int[] bucket = new int[max+1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- >0){
                arr[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
       int[] arr = {323,41,523,6,1,33,4,21};
       RadixSort.radixSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
class RadixSort{
    public static void radixSort(int[] arr){
        if (arr == null || arr.length <2){
            return;
        }
        radixSort(arr,0,arr.length-1,maxbits(arr));
    }

    private static void radixSort(int[] arr, int L, int R, int maxbits) {
        final int radix =10;
        int i = 0,j=0;
        // 有多少个数准备多少辅组空间
        int[] help = new int[R-L+1];
        for (int d = 1; d <= maxbits; d++) {
            //10个空间
            //count[0] 当前位（d位)是0的数字有多少个
            //count[1] 当前位（d位)是0和1的数字有多少个
            //count[2] 当前位（d位)是0，1，2的数字有多少个
            //count[i] 当前位（d位)是0~i的数字有多少个
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i],d);
                count[j]++;
            }
            //构建包含前面数组元素个数的count数组
            for (i = 1; i < radix; i++) {
                count[i] = count[i]+count[i-1];
            }
            //从右向左，构建help数组
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i],d);
                help[count[j]-1] = arr[i];
                count[j]--;

            }
            for (i = L,j=0; i <= R; i++,j++) {
                arr[i] = help[j];
            }
        }

    }

    private static int getDigit(int x, int d) {
        return ((x/((int)Math.pow(10,d-1)))%10);
    }

    private static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int res = 0;
        while (max != 0){
            res++;
            max/=10;
        }
        return res;

    }

}
