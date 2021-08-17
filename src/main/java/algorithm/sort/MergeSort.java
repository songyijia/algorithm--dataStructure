package algorithm.sort;

/**
 * 归并排序，每个部分排序后，将结果合并
 * 将原问题拆分为若干子问题，将子问题的解合并后成为原问题的解。
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5,4,3,6,7,1,2,7,6,8};
        process2(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i+"    ");
        }
    }

    public static void process(int[] arr,int left,int right){
        if (left==right){
            return;
        }
        int mid = left + ((right - left)>>1);
        process(arr,left,mid);
        process(arr,mid+1,right);
        merge(arr,left,mid,right);
    }

    /**
     * 非递归实现
     * @param arr
     * @param left
     * @param right
     */
    public static void process2(int[] arr,int left,int right){
        if (arr == null || left==right){
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while(mergeSize < N){
            int L = 0;
            while (L < N){
                int M = L + mergeSize -1;
                if (M >= N) {
                    break;
                }
                int R = Math.min(M+mergeSize,N-1);
                merge(arr,L,M,R);
                L = R+1;
            }
            if (mergeSize > N /2){
                break;
            }
            mergeSize <<= 1;

        }
    }

    /**
     * 5,4,3,6,7,1,2,7,6,8
     * left=0 right=9 mid=4
     * 5,4,3,6,7                                                    1,2,7,6,8
     * left=0 right=4 mid=2                                         left=5 right=9 mid=7
     * 5,4,3                            6,7
     * left=0 right=2 mid=1             left=3 right=4 mid=3
     * 5,4                          3
     * left=0 right=1 mid=0
     *
     *              左右分区合并。。。。
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right-left+1];
        int i=0;int l = left; int r = mid+1;
        while (l<=mid && r <= right){
            //左右数组比较，将两边小的赋值给新数组
            help[i++] = arr[l]<=arr[r]?arr[l++]:arr[r++];
        }
        //将两边数组剩余元素复制到新数组
        while (l<=mid){
            help[i++] = arr[l++];
        }
        while (r<=right){
            help[i++] = arr[r++];
        }
        //将help数组重写回原数组
//        arr = help;  这样传不过去
        //注意这里的复制是归并的
        for (int j = 0; j < help.length; j++) {
            arr[left+j]= help[j];
        }
    }
}
