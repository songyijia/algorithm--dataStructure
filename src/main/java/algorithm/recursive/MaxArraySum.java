package algorithm.recursive;

public class MaxArraySum {
    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray2(arr));
    }

    private static int maxSubArray(int[] arr,int left,int right,int sum) {
        int max = 0;
        for (int i = left; i <= right; i++) {
            max = Math.max(max, maxSubArray(arr,left+1,right,sum+arr[left]));
        }
        return max;
    }

    private static int maxSubArray2(int[] arr){
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                sum += arr[j];
                max = Math.max(max,sum); //max与每个sum比较
            }
                                        //这里比就错了！！right边界固定了
        }
        return max;
    }

    public static int maxSubArray3(int[] nums) {
        int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0); //当前数加负如加0！
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    public static int maxSubArray4(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
//            arr[i]+=
        }
        return max;
    }
}
