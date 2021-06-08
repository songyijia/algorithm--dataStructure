package algorithm.list;

/**
 * 用递归求数组中最大值
 * Master公式，递归时间复杂度计算
 * T(N) = a*T(N/b) + O(N^d) a b d 都是常数
 */
public class GetMaxInArray {
    public static int getMax(int[] arr,int left,int right){
        if (left == right){
            return arr[left];
        }
        int mid = left+((right-left)>>1);
        int leftMax = getMax(arr,left,mid);
        int rightMax = getMax(arr,mid+1,right);
        return Math.max(leftMax,rightMax);
    }

    public static void main(String[] args) {
        int[] arr = {223,42,22,1134,56,454,22,999};
        System.out.println(getMax(arr,0,arr.length-1));
    }
}
