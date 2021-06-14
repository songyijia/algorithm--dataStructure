package algorithm.sort;

/**
 * 分区问题
 */
public class Partition {
    public static void main(String[] args) {
        int[] arr = {5,4,5,6,7,1,2,7,6,8};
//        System.out.println("lessEqual="+partition(arr,0,arr.length-1));

//        int[] partition = getPartitionFlag(arr,0,arr.length-1);
//        System.out.println("["+partition[0]+","+partition[1]+"]");
//        quickSort1(arr);
//        quickSort2(arr);
        quickSort3(arr);
        for (int i : arr) {
            System.out.print(i+"    ");
        }
    }

    public static int partition(int[] arr,int left,int right){
        if (left>right){
            return left;
        }
        int lessEqual = left-1;
        int index = left;
        int num = arr[right];
        while (index<right){    //比不到最后一个元素
            if (arr[index]<=num){
                //比num小，将当前元素放入less区域，修改两边指针
                swap(arr,index++,++lessEqual);
            } else if (arr[index]>num){
                //比num大，lessEqual位置不动，指针右移
                index++;
            }
        }
        swap(arr,++lessEqual,right);
        return lessEqual;
    }
    /**
     * 荷兰国旗问题
     * 数组中，小于num的数放左边，大于放右边，等于放中间
     * num为数组最右边的数
     * 返回等于num区域的下标
     */
    public static int[] getPartitionFlag(int[] arr,int left,int right){
        if (left>right){
            return new int[]{-1,-1};
        }
        if (left==right){
            return new int[]{left,right};
        }
        int less = left-1; // 为 <num 的指针
//        int more = right-1; //为 >num 的指针
        int more = right; //为 >num 的指针
        int num = arr[right];
        int index = left;
//        while (less<more){
        while (index<more){ // index为比较元素的位置，index前的比较过，后面的没比较过
//            index++;
            if (arr[index] == num){
                index++;
            } else if (arr[index]<num){
                //比num小，将当前元素放入less区域，修改两边指针
                swap(arr,index++,++less);
            } else if (arr[index]>num){
                //比num大，当前位置不动，放入more区域，只改more指针
                swap(arr,index,--more);
            }
        }
        swap(arr,more,right);
        return new int[]{less+1,more};
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
    }

    public static void quickSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process1(arr,0,arr.length-1);
    }

    private static void process1(int[] arr, int L, int R) {
        if (L>=R){
            return;
        }
        int M = partition(arr,L,R);
        process1(arr, L, M-1);
        process1(arr, M+1, R);
    }
    public static void quickSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process2(arr,0,arr.length-1);
    }

    private static void process2(int[] arr, int L, int R) {
        if (L>=R){
            return;
        }
        int[] M = getPartitionFlag(arr,L,R);
        process2(arr, L, M[0]-1);
        process2(arr, M[1]+1, R);
    }
    public static void quickSort3(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process3(arr,0,arr.length-1);
    }

    private static void process3(int[] arr, int L, int R) {
        if (L>=R){
            return;
        }
        swap(arr,L+(int)(Math.random()*(R-L+1)),R);
        int[] M = getPartitionFlag(arr,L,R);
        process2(arr, L, M[0]-1);
        process2(arr, M[1]+1, R);
    }
}
