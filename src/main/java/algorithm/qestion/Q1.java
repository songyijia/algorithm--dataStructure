package algorithm.qestion;

/**
 * 给定一个有序数组arr,从左到右依次表示X轴上从左往右的位置
 * 给定一个正整数K,返回如果有一根长度为k的绳子，最多能盖住几个点
 */
public class Q1 {

    public static int maxPoint1(int[] arr,int L){
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr,i,arr[i]-L);
            res = Math.max(res,i-nearest+1);
        }
        return res;
    }

    private static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R){
            int mid = L+((R-L)>>1);
            if (arr[mid]>=value){
                index= mid;
                R = mid - 1;
            } else {
                L = mid+1;
            }
        }
        return index;
    }

    public static int maxPoint2(int[] arr,int L){
        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;
        while (left < N){
            while (right < N && arr[right]-arr[left] <= L){
                right++;
            }
            max = Math.max(max,right-(left++));
        }
        return max;
    }
}
