package algorithm.recursive;

/**常见的4种尝试模型
 * 1.从左往右 2.范围上的 3.多样本位置全对应 4.寻找业务限制的
 * 1--N的位置，机器人在中间位置M，只能在数组中移动
 * 求出必须要移动k步时到 P，所有方法总数
 */
public class MoveStep {
    public static int moveStep(int N,int M,int P,int K){
        return walk(N,M,K,P);
    }

    /**
     * 暴力 存在重复计算的过程
     * 动态规划来自于某个暴力递归
     * @param N
     * @param cur
     * @param rest
     * @param P
     * @return
     */
    private static int walk(int N, int cur, int rest, int P) {
        /**
         * 没有剩余步数了，当前cur位置就是最后位置
         * 如果最后停在P,则之前做法有效
         * 如果最后没停在P,则之前做法无效
         */
        if (rest == 0) {
            return cur==P ? 1:0;
        }
        /**
         * 如果还有rest步可以走，cur在1位置时，只能走向2，在N时，只能走向N-1
         */
        if (cur == 1){
            return walk(N,2,rest-1,P);
        }
        if (cur == N){
            return walk(N,N-1,rest-1,P);
        }
        return walk(N,cur+1,rest-1,P)+walk(N,cur-1,rest-1,P);
    }

    public static int moveStep2(int N,int M,int K,int P){
        int[][] dp = new int[N+1][K+1];
        for (int i = 0; i <=N; i++) {
            for (int j = 0; j <=K; j++) {
                dp[i][j] = -1;
            }

        }
        return walkCache(N,M,K,P,dp);
    }

    private static int walkCache(int N, int cur, int rest, int P, int[][] dp) {
        if (dp[cur][rest]==-1){
            return dp[cur][rest];
        }
        if (rest == 0){
            dp[cur][rest] = cur == P ? 1: 0;
            return dp[cur][rest];
        }
        if (cur == 1){
            dp[cur][rest] = walkCache(N,2,rest-1,P,dp);
            return dp[cur][rest];
        }
        if (cur == N){
            dp[cur][rest] = walkCache(N,N-1,rest-1,P,dp);
            return dp[cur][rest];
        }
        dp[cur][rest] = walkCache(N,cur+1,rest-1,P,dp)
                +walkCache(N,cur-1,rest-1,P,dp);
        return dp[cur][rest];
    }
}
