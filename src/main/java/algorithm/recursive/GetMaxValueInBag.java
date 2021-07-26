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
}
