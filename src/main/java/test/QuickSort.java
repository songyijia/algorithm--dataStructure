package test;

public class QuickSort {
    public static void quickSort(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }
        process(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private static void process(int[] arr,int L,int R){
        if (L>R){
            return;
        }
        int[] M = getPartition(arr,L,R);
        process(arr,L,M[0]-1);
        process(arr,M[1]+1,R);
    }

    /**
     * 返回的是相等区域的点下标
     * @param arr
     * @param L
     * @param R
     * @return
     */
    private static int[] getPartition(int[] arr, int L, int R) {
        if (L>R) {
            return new int[]{-1, -1};
        }
        if (L==R){ //base case 2
            return new int[]{L,L};
        }
        int less = L-1;
        int more = R;
        int num = arr[R];
        int index = L;
        while (index < more) {
            if (arr[index] == num){
                index++;
            } else if (arr[index] < num){
                swap(arr,++less,index++);
            } else {
                swap(arr,--more,index);
            }
        }
        swap(arr,more,R);
        return new int[]{less+1,more};
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,1,5,7,2,8,3};
        quickSort(arr);
    }
}
