package algorithm.recursive;

/**
 * 从左往右的尝试
 * 规定1和A对应，2和B对应，3和C对应，那么111可以转化为“AAA","KA"和"AK"
 * 给定一个只有数字字符的字符串str,返回有多少种转化结果
 */
public class Number2Char {
    public static int number2Char(String str){
        if (str.length()==0){
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars,0);
    }

    private static int process(char[] str, int i) {
        if (i ==str.length){
            return 1;
        }
        // i到终止位置
        if (str[i]=='0'){
            return 0;
        }
        //i没到终止位置，str[i]字符不是‘0’
        if (str[i] == '1'){
            int res = process(str,i+1);//i自己作为单独部分，后续有多少种方式
            if (i+1 < str.length) {
                res+=process(str,i+2);//（i和i+1）作为单独部分，后续有多少种方式
            }
            return res;
        }
        if (str[i] == '2'){
            int res = process(str,i+1);
            if (i+1 <str.length &&(str[i] >= '0' && str[i] <= '6')){
                res+=process(str,i+2);
            }
            return res;
        }
        // str[i] == '3 ~ 9'
        return process(str,i+1);
    }

    public static int dpNumber2Char(String str){
        if (str.length()==0){
            return 0;
        }
        char[] chars = str.toCharArray();

        return dpWay(chars,chars.length);
    }

    private static int dpWay(char[] chars, int N) {
        int[] dp = new int[chars.length+1];
        dp[N] = 1;
        for (int i = N-1; i >= 0 ; i--) {
            if (chars[i]=='0'){
                dp[i] = 0;
            }
            if (chars[i] == '1'){
                dp[i] = dp[i+1];
                if (i+1 < chars.length){
                    dp[i]+=dp[i+2];
                }
            }
            if (chars[i]=='2'){
                dp[i] = dp[i+1];
                if (i+1 <chars.length &&(chars[i] >= '0' && chars[i] <= '6')){
                    dp[i]+=dp[i+2];
                }
            }
        }
        return dp[0];

    }

    public static void main(String[] args) {
        System.out.println(number2Char("11111"));
        System.out.println(dpNumber2Char("11111"));
    }
}
