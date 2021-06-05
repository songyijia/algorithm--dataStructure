package algorithm.sort;

/**
 * 选择排序 每次从数组中选出最小的放最前面
 *  5 4 2 1 7 9 3
 *  时间复杂度 O(n^2)
 *  注意：前i个是排好序最小的，后面都是从i后面里面选出最小的，再往后排
 */
public class SelectSort {

    public static int[] selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i; j <= arr.length - 1; j++) {
                if (arr[j]<arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(i,minIndex,arr);
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
        printlnArr(selectSort(arr));
    }
}
