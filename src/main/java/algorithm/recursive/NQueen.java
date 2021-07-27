package algorithm.recursive;

/**
 * N皇后问题
 *
 */
public class NQueen {
    public static int num1(int n){
        if (n <1){
            return 0;
        }
        int[] record = new int[n];
        return process1(0,record,n);
    }
    /**
     *
     * @param i 来到第i行
     * @param record 皇后位置
     * @param n 几个皇后
     * @return
     */
    public static int process1(int i,int[] record,int n){
        if (i==n){ //终止行
            return 1;
        }
        //没有到终止位置，还有皇后要放
        int res = 0;
        for (int j = 0; j < n; j++) { //当前在i行，尝试所有列
            /**
             * 当前i行的皇后，放在j列，会不会和之前的皇后同列或同斜线
             */
            if (isValid(record,i,j)){
                record[i] = j;
                res += process1(i+1,record,n);
            }
        }
        return res;

    }

    public static boolean isValid(int[] record,int i,int j){
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k]-j)==Math.abs(i-k)){
                return false;
            }
        }
        return true;
    }
    /**
     *
     * @param limit 问题的规模
     * @param colLim 列限制
     * @param leftDiaLim 左斜线
     * @param rightDiaLim 右斜线
     * @return
     */
    public static int process2(int limit,int colLim,int leftDiaLim,int rightDiaLim){
        if (colLim == limit){
            return 1;
        }
        /**
         * 所有能放皇后的位置，都在pos上
         *
         */
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0){
            //取出pos中，最右侧的1来，剩下位置都是0
            mostRightOne = pos & (~pos +1);
            pos = pos - mostRightOne;
            res += process2(limit,colLim | mostRightOne,
                    (leftDiaLim|mostRightOne) <<1,
                    (rightDiaLim|mostRightOne) >>1);

        }
        return res;
    }

    //不要超过32皇后
    public static int num2(int n){
        if (n<1 || n> 32){
            return 0;
        }
        int limit = n == 32 ? -1:(1<<n)-1;
        return process2(limit,0,0,0);
    }
}
