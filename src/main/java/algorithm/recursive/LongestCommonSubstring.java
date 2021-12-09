package algorithm.recursive;

/**
 * 字符串最长公共子序列
 *
 * 【尝试模型：多样本位置全对应】
 *  str1="a123bc"    str2="12dea3f"
 *   0 1 2 3 4 5 6 ----s2
 * 0 0 0 0 0 1 1 1
 * 1 1
 * 2 1
 * 3 1
 * 4 1
 * 5 1          *
 * s1
 *  dp[i][j] 指s1从0到i,和s2从0到j最长公共子序列有多长
 *  对于两个串：1，公共子序列可以都不以i,j为结尾，及dp[i][j] == dp[i-1][j-1]
 *              2,可能以str1的i结尾，不以str2的j结尾。dp[i][j-1]
 *              3.可能不以str1的i结尾，以str2的j结尾.dp[i-1][j]
 *              4.以str1的i结尾，以str2的j结尾。需要str1[i]==str2[j].d[i-1][j-1]+1
 *
 */
public class LongestCommonSubstring {
    public static int lcs(String s1,String s2){
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[][] dp = new int[s1.length()][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 :0;
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0],str1[i]==str2[0]?1:0);
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = Math.max(dp[0][j-1],str1[0]==str2[j]?1:0);
        }

        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                /**
                 * （i-1,j),(i,j-1),(i-1,j-1)结束
                 */
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]); //可能性2，3的最大值，这两个位置都与它左边上边，左上做过决策。
                // 以（i,j）结束的条件是 si == sj 且 dp[i-1][j-1]+1【base case】
                if (str1[i]==str2[j]){
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1]+1);//可能性2，3的最大值与可能性4比较
                }
            }
        }
        return dp[str1.length-1][str2.length-1];
    }

    public static void main(String[] args) {
        /**
         *      a   1   b   2   x    3
         *   q  0   0   0   0   0   0
         *   1  0   1   1   1   1   1
         *   w  0   1   1   1   1   1
         *   2  0   1   1   2   2   2
         *   r  0   1   1   2   2   2
         */
        System.out.println(lcs("a1b2x3","q1w2r"));
    }
}
