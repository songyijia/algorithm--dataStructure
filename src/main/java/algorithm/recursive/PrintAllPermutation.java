package algorithm.recursive;

import java.util.ArrayList;

/**
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
     * i终止位置，chars当然的样子，就是一种结果 -》 ans
     * @param chars
     * @param i
     * @param res
     */
    private static void process(char[] chars, int i, ArrayList<String> res) {
        if (i == chars.length) {
            res.add(String.valueOf(chars));
        }
        for (int j = 0; j < chars.length; j++) {
            swap(chars,i,j);
            process(chars,i+1,res);
            swap(chars,i,j); //恢复现场
        }
    }

    /**
     * 不重发的全排列
     * @param str
     * @param i
     * @param res
     */
    public static void process2(char[] str,int i,ArrayList<String> res){
        if (i==str.length){
            res.add(String.valueOf(str));
            return;
        }
        boolean[] visit = new boolean[26];
        for (int j = 0; j < str.length; j++) {
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
}
