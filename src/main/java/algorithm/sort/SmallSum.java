package algorithm.sort;

/**
 * 小数求和  当前数前面所有比它小的之和
 * 【1，4，3，2，5】
 * 求和：1
 *       1
 *       1
 *       1,4,3,2
 *
 *  逆数对
 */
public class SmallSum {
    public static void main(String[] args) {
        int[] arr = {5,4,3,6,7,1,2,7,6,8};
        /**   5,4,3,6,7  | 1,2,7,6,8
         *    4,5 |3,6,7
         *     3,4,5,6,7
         *     sum=4*2+5*2
         */
//        int smallSum = process(arr,0,arr.length-1);
//        System.out.println("smallSum : "+smallSum);
//        for (int i : arr) {
//            System.out.print(i+"    ");
//        }
        System.out.println();
        int descSortSum = descSortPair(arr,0,arr.length-1);
        System.out.println("descSortSum : "+descSortSum);
        for (int i : arr) {
            System.out.print(i+"    ");
        }


    }

    public static int process(int[] arr,int left,int right){
        if (left==right){
            return 0;
        }
        int mid = left + ((right - left)>>1);
        return process(arr,left,mid)+
        process(arr,mid+1,right)+
        merge(arr,left,mid,right);
    }

    /**
     * 求逆序对
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @return
     */
    public static int descSortPair(int[] arr,int left,int right){
        if (left==right){
            return 0;
        }
        int mid = left + ((right - left)>>1);
        return descSortPair(arr,left,mid)+
                descSortPair(arr,mid+1,right)+
                descSortmerge(arr,left,mid,right);
    }

    private static int descSortmerge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right-left+1];
        int i=0;int l = left; int r = mid+1;
        int res = 0;
        while (l<=mid && r <= right){
            int temp = 0;
            if (arr[l]>arr[r]){
                //只有左边比右边小，左指针才增加
                temp = arr[l++];
                res+=(right-r+1);
            } else {
                temp = arr[r++];
            }
            help[i++] = temp;
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
        return res;
    }


    private static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right-left+1];
        int i=0;int l = left; int r = mid+1;
        int res = 0;
        while (l<=mid && r <= right){
            //左右数组比较，将两边小的赋值给新数组
//            help[i++] = arr[l]<arr[r]?arr[l++]:arr[r++];
            int temp = 0;
            if (arr[l]<arr[r]){
                //只有左边比右边小，左指针才增加
                temp = arr[l++];
                res+=temp*(right-r+1);
            } else {
                temp = arr[r++];
            }
            help[i++] = temp;
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
        return res;
    }
}
