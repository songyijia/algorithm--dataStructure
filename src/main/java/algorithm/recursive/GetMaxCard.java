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
        return Math.max(f(arr,0,arr.length),s(arr,0,arr.length-1));
    }

    //后手拿牌
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

    public static void main(String[] args) {
        int[] arr = {4,7,9,5};
        System.out.println(f(arr,0,3));
        System.out.println(s(arr,0,3));
    }
}
