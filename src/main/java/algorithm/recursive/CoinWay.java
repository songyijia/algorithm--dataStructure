package algorithm.recursive;

/**题----找到---》暴力递归的解法（尝试）（有重复解）-----可变参数（不讲究组织，有解就用，无解就递归算）---》记忆化搜索（如果有枚举行为）----精细化组织----》经典动态规划
 *背包问题时间复杂度：暴力递归：2^N,记忆化搜索：N*bag
 *
 * 组币问题
 * 钱币数组【3，7，10，50】 组成1000，有多少种办法？
 *
 */
    public class CoinWay {
    public static int coinWay(int[] arr,int aim){
        if (arr==null||arr.length==0||aim<0){
            return 0;
        }
        return process(arr,0,aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        // rest>=0
        if (index == arr.length){ //没有货币可以选择
            return rest==0?1:0;
        }
        int ways = 0;
        for (int zhang = 0; zhang*arr[index]<=rest; zhang++) {
            ways+=process(arr,index+1,rest-zhang*arr[index]);
        }
        return ways;

    }

    /**
     * 记忆化搜索
     * @param arr
     * @param aim
     * @return
     */
    public static int way2(int[] arr,int aim){
        if (arr==null||arr.length==0||aim<0){
            return 0;
        }
        int[][] dp = new int[arr.length+1][aim+1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j]=-1;
            }
        }
        return process2(arr,0,aim,dp);
    }

    /**计划搜索的方法
     * 如果index和rest组合是没算过 ，dp[index][rest]=-1
     * 算过的，dp[index][rest]>-1
     * @param arr
     * @param index
     * @param rest
     * @param dp
     * @return
     */
    private static int process2(int[] arr, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        // rest>=0
        if (index == arr.length){ //没有货币可以选择
            dp[index][rest]=rest==0?1:0;
            return dp[index][rest];
        }
        int ways = 0;
        for (int zhang = 0; zhang*arr[index]<=rest; zhang++) {
            ways+=process2(arr,index+1,rest-zhang*arr[index],dp);
        }
        dp[index][rest] = ways;
        return ways;

    }

    /**
     * 第一版经典动态规划，但每个格子有枚举行为
     * @param arr
     * @param aim
     * @return
     */
    public static int way3(int[] arr,int aim){
        if (arr==null||arr.length==0||aim<0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[arr.length+1][aim+1];
        dp[N][0]=1;//dp[N][1...aim]=0
        for (int index = N-1; index >= 0 ; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang*arr[index]<=rest; zhang++) {
                    ways+=dp[index+1][rest-zhang*arr[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    /**
     * 斜率优化的技巧，四边形不等式的技巧,空间压缩，状态化简
     * 省略枚举部分的动态规划
     * @param arr
     * @param aim
     * @return
     */
    public static int way4(int[] arr,int aim){
        if (arr==null||arr.length==0||aim<0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0]= 1;
        for (int index = N-1; index >= 0 ; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest]=dp[index+1][rest];
                if (rest-arr[index]>=0) {
                    dp[index][rest] += dp[index][rest-arr[index]];
                }
            }
        }
        return dp[0][aim];
    }
    public static void main(String[] args) {
        int[] arr={5,10,50,100};
        int sum = 1000;
        System.out.println(coinWay(arr,sum));
        System.out.println(way2(arr,sum));
        System.out.println(way3(arr,sum));
    }
}
