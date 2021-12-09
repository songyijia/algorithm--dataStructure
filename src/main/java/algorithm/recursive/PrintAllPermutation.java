package algorithm.recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/** 【全排列：字符调换顺序，来回倒腾】
 * 打印字符串的全排列
 */
public class PrintAllPermutation {
    public static ArrayList<String> permutation(String str){
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0){
            return res;
        }
        char[] chars = str.toCharArray();
        process(chars,0,res);
        return res;
    }

    /**
     * chars[0...i-1]已经做好决定的
     * chars[i...]都有机会来到i位置
     * i终止位置，chars当前的样子，就是一种结果 -》 ans
     * @param chars
     * @param i    当前来到的位置
     * @param res
     */
    private static void process(char[] chars, int i, ArrayList<String> res) {
        if (i == chars.length) {
            res.add(String.valueOf(chars));
//            return;
        }
        //从i位置后面继续尝试，i前面已经决策了
        for (int j = i; j < chars.length; j++) { //所有char都可能来到i
            swap(chars,i,j);
            process(chars,i+1,res);
            swap(chars,i,j); //恢复现场
        }
    }

    public static ArrayList<String> permutationNoRepeat(String str){
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0){
            return res;
        }
        char[] chars = str.toCharArray();
        process2(chars,0,res);
        return res;
    }

    /**
     * 不重发的全排列
     * @param str
     * @param i
     * @param res 如果用Set是暴力递归加过滤，此处用的是分支限界！！提前杀死指路，不是计划搜索！！
     */
    public static void process2(char[] str,int i,ArrayList<String> res){
        if (i==str.length){
            res.add(String.valueOf(str));
//            return;
        }
        boolean[] visit = new boolean[26];
        //从i位置后面继续尝试，i前面已经决策了
        for (int j = i; j < str.length; j++) {
            if (!visit[str[j]-'a']){ //排除走过的分支
                visit[str[j]-'a'] = true;
                swap(str,i,j);
                process2(str,i+1,res);
                swap(str,i,j);
            }
        }
    }
    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;

    }

    public static void main(String[] args) {
        System.out.println(permutation("abc"));
        System.out.println(permutationNoRepeat("abc"));
    }
}
