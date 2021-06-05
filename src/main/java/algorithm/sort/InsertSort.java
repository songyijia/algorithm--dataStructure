package algorithm.sort;

/**
 * 插入排序
 * 从第二位往前比较，若比它大，则交换
 * 时间复杂度 O(n^2) 若是基本排好序的则效率高
 * 注意：插入新元素都是与前i个排好序的老元素比较
 */
public class InsertSort {
    public static int[] insertSort(int[] arr){
        for (int i = 1; i <= arr.length - 1; i++) {
            int j=i;
            for (; j > 0 ; j--) {
                if (arr[j]<arr[j-1]){
                    swap(j,j-1,arr);
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
        printlnArr(insertSort(arr));
    }
}
