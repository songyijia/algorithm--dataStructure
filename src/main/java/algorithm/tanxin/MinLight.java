package algorithm.tanxin;

import java.util.HashSet;

/**
 * 最少的灯能照亮街道
 */
public class MinLight {

    public static int minLight1(String road){
        if (road == null || road.length() == 0){
            return 0;
        }
        return process(road.toCharArray(),0,new HashSet<>());
    }

    /**
     * str[index...]位置，自由选择放灯还是不放灯
     * str[0...index-1]位置，表示已经做完决定，哪些放了灯的位置，存在lights里
     * 要求选出能照亮所有.的方案，并且在有效方案中，找出最少灯的方案
     * @param str
     * @param index
     * @param lights
     * @return
     */
    private static int process(char[] str, int index
            , HashSet<Object> lights) {
        if (index == str.length){//结束的时候
            for (int i = 0; i < str.length; i++) {  //验证目前能否将所有街道照亮
                if (str[i] != 'X') { //当前位置是.
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE; //返回无效解
                    }
                }
            }
            return lights.size();//返回有效解
        } else {
            // index 位置不放灯的情况
            int no = process(str,index+1,lights);
            int yes = Integer.MAX_VALUE;
            // index位置是. 放灯
            if (str[index] == '.'){
                lights.add(index);
                yes = process(str,index+1,lights);
                lights.remove(index); //共用老的结构，算完后删掉，恢复现场
            }
            return Math.min(no,yes);
        }

    }

    /**
     *  贪心解法在于题，每到题解法不同，此题根据题意一次遍历解决。
     * @param road
     * @return
     */
    public static int minLight2(String road){
        char[] str = road.toCharArray();
        int index= 0 ,light = 0;
        while (index < str.length){
            if (str[index] == 'X'){
                index++;
            } else { // i-> .
                light++;
                if (index+1 == str.length){
                    break;
                } else {
                    if (str[index+1] == 'X'){ // . X
                        index = index+2;
                    } else {                // . . . 或 。。X 都去 i+3上做决定
                        index = index+3;
                    }
                }
            }
        }
        return light;
    }

    public static void main(String[] args) {
//        String road = "..X..XX.....X.";
        String road = "....";
        System.out.println(minLight1(road));
        System.out.println(minLight2(road));
    }
}
