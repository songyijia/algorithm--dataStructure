package algorithm.tanxin;

import java.util.PriorityQueue;

/**
 * 分金问题，将金条分成若干段，每段的收费为金条长度，
 * 10 20 30 若 60 先分 10+50 ，50再分 20，30，则花费110
 * 若先分30，30 ，再将30分10，20，则花费90.
 */
public class LessMoneySplitGold {
    public static int lessMoney1(int[] arr){
        if (arr == null||arr.length==0){
            return 0;
        }
        return process(arr,0);
    }

    private static int process(int[] arr, int pre) {
        if (arr.length == 1){
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                ans = Math.min(ans,process(copyAndMergeTwo(arr,i,j),pre+arr[i]+arr[j]));
            }
        }
        return ans;
    }

    private static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length-1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j){
                ans[ansi++] = arr[arri];
            }
        }
        ans[ansi] = arr[i]+arr[j];
        return ans;
    }

    /**
     * 小根堆解决
     * @param arr
     * @return
     */
    public static int lessMoney2(int[] arr){
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0,cur=0;
        while (pQ.size()>1){
            cur = pQ.poll()+pQ.poll();
            sum+=cur;
            pQ.add(cur);
        }
        return sum;
    }
}
