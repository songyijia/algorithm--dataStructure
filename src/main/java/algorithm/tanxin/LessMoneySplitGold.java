package algorithm.tanxin;

import java.util.PriorityQueue;

/**
 * 分金问题，将金条分成若干段，每段的收费为金条长度，
 * 10 20 30 若 60 先分 10+50 ，50再分 20，30，则花费110
 * 若先分30，30 ，再将30分10，20，则花费90.
 *      60
 *   30   30
 * 10 20
 * 分金计算转化为合金：30+60=90
 *      60
 *   40   20
 * 10 30
 * 最少：40+60=100
 */
public class LessMoneySplitGold {
    public static int lessMoney1(int[] arr){
        if (arr == null||arr.length==0){
            return 0;
        }
        return process(arr,0);
    }

    /**
     * 暴力递归
     * @param arr 分成小块
     * @param pre 已花费金额
     * @return
     */
    private static int process(int[] arr, int pre) {
        if (arr.length == 1){ //合并完数组为1，即分金结束
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) { //两两组合的结果
                ans = Math.min(ans,process(copyAndMergeTwo(arr,i,j),pre+arr[i]+arr[j]));
            }
        }
        return ans;
    }

    private static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length-1];
        int ansi = 0;
        //复制其它块
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j){
                ans[ansi++] = arr[arri];
            }
        }
        //目标块合并
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
        //利用小根堆将金块从小到达排序后，从小块可以加，最后得到的值定为最小。
        int sum = 0,cur=0;
        while (pQ.size()>1){
            cur = pQ.poll()+pQ.poll();
            sum+=cur;
            pQ.add(cur);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {60,10,20,30};
        System.out.println(lessMoney1(arr));
        System.out.println(lessMoney2(arr));
    }
}
