package algorithm.recursive;

/**   范围尝试模型
 *
 * 摸牌游戏
 * [70,100,1,4]
 * 两个玩家从数组两边抽牌，两人很聪明，都要拿最大的牌，
 * 甲：4，100
 * 乙：70，1
 */
public class GetMaxCard {

    public static int win1(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        return Math.max(f(arr,0,arr.length-1),s(arr,0,arr.length-1));
    }

    /**
     * 后手拿牌
     * @param arr  牌堆
     * @param i     左指针
     * @param j     右指针
     * @return
     */
    private static int s(int[] arr, int i, int j) {
        if (i == j){
            return 0;
        }
        return Math.min(f(arr,i+1,j),f(arr,i,j-1));
    }

    //先手拿牌
    private static int f(int[] arr, int i, int j) {
        if (i == j){
            return arr[i];
        }
        return Math.max(arr[i]+s(arr,i+1,j),
                arr[j]+s(arr,i,j-1));
    }

    public static int win2(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i];
        }
        //s[i][i]=0;
        //完成两个正方形的数字填写
        for (int i = 1; i < N; i++) {
            int L = 0,R = i;
            while (L < N && R <N){
                f[L][R] = Math.max(arr[L]+s[L+1][R],arr[R]+s[L][R-1]);
                s[L][R] = Math.min(f[L+1][R],f[L][R-1]);
                L++;
                R++;
            }

        }
        return Math.max(f[0][N-1],s[0][N-1]);

    }

    public static void main(String[] args) {
        int[] arr = {4,7,9,5};
        System.out.println(f(arr,0,3));
        System.out.println(s(arr,0,3));
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }
}
