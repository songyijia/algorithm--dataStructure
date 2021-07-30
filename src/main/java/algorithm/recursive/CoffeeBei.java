package algorithm.recursive;

/**业务限制模型/从左往右的模型
 * 给定一个数组，代表每个人喝完咖啡准备刷杯子的时间
 * 只有一个咖啡机，一次只能洗一个杯子，时间耗时a,洗完才能洗下一杯
 * 每个咖啡杯也可自己挥发干净，耗时b,咖啡杯可以并行挥发，
 * 返回让所有咖啡杯变干净的最早完成时间
 * 三个参数：int[] arr,int a,int b
 */
public class CoffeeBei {
    /**
     *
     * @param drinks 每一个员工喝完的时间 固定变量
     * @param a 洗一杯的时间，固定变量
     * @param b 晾干的时间 固定变量
     * @param index 从index开始求解，前面已经洗干净，index后面都想变干净
     * @param washLine 洗的机器何时可用
     * @return
     */
    public static int process(int[] drinks,int a,int b,int index,int washLine){
        if (index==drinks.length-1){
            return Math.min(Math.max(washLine,drinks[index])+a,drinks[index]+b);
        }
        //wash是我当前的咖啡杯洗完时间
        int wash = Math.max(washLine,drinks[index])+a;
        int next1 = process(drinks, a, b, index + 1, wash);
        int p1 = Math.max(wash, next1);
        int dry = drinks[index] + b;
        int next2 = process(drinks, a, b, index + 1, washLine);
        int p2 = Math.max(dry, next2);
        return Math.min(p1,p2);
    }

    public static int dp(int[] drinks,int a,int b){
        if (a>=b){
            return drinks[drinks.length-1]+b;
        }
        //a<b
        int N = drinks.length;
        int limit = 0; //咖啡机什么时候可用
        for (int i = 0; i < N; i++) {
            limit = Math.max(limit,drinks[i])+a;

        }
        int[][] dp = new int[N][limit+1];
        for (int washLine = 0; washLine <= limit; washLine++) {
            dp[N-1][washLine] = Math.min(Math.max(washLine,drinks[N-1])+a,drinks[N-1]+b);
        }
        for (int index = N-2; index >= 0; index--) {
            for (int washLine = 0; washLine <=limit; washLine++) {
                //wash是我当前的咖啡杯洗完时间
                int wash = Math.max(washLine,drinks[index])+a;
                int p1 = Integer.MAX_VALUE;
                if (wash <= limit) {
                    p1 = Math.max(wash, dp[index+1][wash]);
                }
                int dry = drinks[index] + b;
                int p2 = Math.max(dry, dp[index+1][washLine]);
                dp[index][washLine] = Math.min(p1,p2);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1,1,2,4,5,6,6,7,7,12,12,15};
        int a = 3,b=10;
        System.out.println(process(arr,a,b,0,0));
        System.out.println(dp(arr,a,b));
    }
}
