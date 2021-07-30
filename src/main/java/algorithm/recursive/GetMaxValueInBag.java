package algorithm.recursive;

/**
 * 背包问题
 */
public class GetMaxValueInBag {
    public static int maxValue(int[] w,int[] v,int bag){
        return process(w,v,0,bag);
    }

    /**
     *
     * @param w
     * @param v
     * @param index 。。。 货物自由选择，但剩余空间不小于0
     * @param rest 剩余空间
     * @return 返回 index...货物能够获得的最大价值
     */
    private static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0){ // base case 1
            return -1;
        }
        // rest >=0
        if (index == w.length){ //base case 2
            return 0;
        }
        //有货也有空间
        int p1 = process(w, v, index + 1, rest);
        int p2 = -1;
        int p2Next = process(w, v, index + 1, rest - w[index]);
        if (p2Next !=-1){
            p2 = v[index]+p2Next;
        }
        return Math.max(p1,p2);
    }

    public static int dpMaxValue(int[] w,int[] v,int bag){
        return dpWay(w,v,0,bag);
    }

    private static int dpWay(int[] w, int[] v, int i, int bag) {
        int N = w.length;
        int[][] dp = new int[N+1][bag+1];
        //dp[N][..]=0
        //N行填完了，从N-1行开始填,,?? (依赖index+1) 从二维表中看出要返回的是dp[0][bag]
        for (int index = N-1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index+1][rest];
                int p2 = -1;
                int p2Next = dp[index+1][rest-w[index]];
                if (rest-w[index]>=0){
                    p2 = v[index] + p2Next;
                }
                dp[index][rest]=Math.max(p1,p2);
            }

        }
        return dp[0][bag];

    }

    public static void main(String[] args) {
        int[] weights = {3,2,4,6};
        int[] values = {5,6,4,19};
        int bag =11;
        System.out.println(maxValue(weights,values,bag));
        System.out.println(dpMaxValue(weights,values,bag));
    }
}
