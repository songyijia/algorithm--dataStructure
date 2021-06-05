package algorithm.sort;

/**
 * 布尔排序，每个元素依次与后面的比较并【两两交换】位置
 *
 * 注意：冒泡完的前i个元素不用再比较
 * 时间复杂度：O(n^2)
 */
public class BubblebSort {

    public static int[] bubbleSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1-i; j++) {
                if (arr[j+1]<arr[j]){
                    swap(j+1,j,arr);
                }
            }
        }
        return arr;
    }
    private static void swap(int i, int min,int[] arr) {
        int temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
    }

    public static void printlnArr(int[] arr){
        for (int i : arr) {
            System.out.print(i+"  ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,4 ,2 ,1, 7, 9, 3};
        printlnArr(bubbleSort(arr));
    }
}
