package algorithm;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 1、给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 示例 1：
 输入：s = "ADOBECODEBANC", t = "ABC"
 输出："BANC"
 示例 2：
 输入：s = "a", t = "a"
 输出："a"
 提示：
 1 <= s.length, t.length <= 105
 s 和 t 由英文字母组成
 */
public class HWTest1 {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC",t="ABC";
        char[] chars = s.toCharArray();
        ArrayList<String> ans = new ArrayList<>();
//        getAllSubString(chars,0,ans,"",0);
        getAllSubStr(chars,ans);
        System.out.println(ans);
        String minSubStr = s;
        char[] tChars = t.toCharArray();
        for (String str : ans) {
            boolean flag = true;
            for (char tChar : tChars) {
                if (!str.contains(Character.toString(tChar))){
                    flag = false;
                }
            }
            if (flag && str.length()<minSubStr.length()){
                minSubStr = str;
            }
        }
        System.out.println(minSubStr);
    }

    private static void getAllSubString(char[] chars, int index, ArrayList<String> ans,String s, int end) {
        if (chars.length == end){
//            if (s.equals(new String(chars))){
//                //不讲原字符串放进去
//                return;
//            }
            index++;
            s = "";
            if (chars.length == index){
                return;
            }
        }
        ans.add(s);

//        String no = s;
//        getAllSubSeq(chars,index+1,ans,s);
        String yes = s+String.valueOf(chars[end]);
        getAllSubString(chars,index,ans,yes,end+1);
    }

    private static void getAllSubStr(char[] chars,ArrayList<String> list){
        for (int i = 0; i < chars.length; i++) {
            String s = String.valueOf(chars[i]);
            list.add(s);
            for (int j = i+1; j < chars.length; j++) {
                s += String.valueOf(chars[j]);
                list.add(s);
            }
        }
    }

    /**
     * 2、给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     回文串 是正着读和反着读都一样的字符串。
     示例 1：
     输入：s = "aab"
     输出：[["a","a","b"],["aa","b"]]
     示例 2：
     输入：s = "a"
     输出：[["a"]]

     提示：
     1 <= s.length <= 16
     s 仅由小写英文字母组成
     */
    public static class HWTest2 {
        public static void main(String[] args) {
            String s = "aababca";
            char[] chars = s.toCharArray();
            ArrayList<String> ans = new ArrayList<>();
            getAllSubStr(chars,ans);
            //在子串里面找回文子串
            for (String an : ans) {
                if (checkHuiWenStr(an)){
                    System.out.println(an);
                }
            }
            System.out.println("=================");
            System.out.println(longestHuiWen(s));
        }

        private static String longestHuiWen(String str){
            char[] chars = str.toCharArray();
            String ans = "";
            int maxLen = -1;
            int start = 0,end = 0;
            for (int i = 0; i < chars.length; i++) {
                int len1 = spanAroundCenter(chars,i,i);    //奇数      aba
                int len2 = spanAroundCenter(chars,i,i+1); //偶数 abba
                int len = Math.max(len1,len2);
                if (len > maxLen){
                    //新的子串大，调整子串的上下边界。。。。
                    maxLen = len;
                    if (len2 > len1){
                        start = i-len/2+1;
                        end = i+len/2;
                    } else {
                        start = i-len/2;
                        end = i+len/2;
                    }
                }
            }
            return str.substring(start+1,end);
        }

        /**
         * 由中间开始向两边找
         * @param chars
         * @param left
         * @param right
         * @return
         */
        private static int spanAroundCenter(char[] chars, int left, int right) {
            int len = 0;
            while (left>=0 && right < chars.length){
                if (chars[left] == chars[right]){
                    len++;
                } else {
                    return right-left+1;
                }
                left--;
                right++;
            }
            return right-left-1;
        }

        private static boolean checkHuiWenStr(String str){
            char[] chars = str.toCharArray();
            //一次遍历！！！
//            Stack<Character> stack = new Stack<>();
//            for (char aChar : chars) {
//                stack.push(aChar);
//            }
//            StringBuilder stringBuilder = new StringBuilder();
//            while (!stack.isEmpty()){
//                stringBuilder.append(stack.pop());    //在stack弹出时比，只遍历2次
//            }
//            return str.equals(stringBuilder.toString()); //太傻了，遍历了3次
            int i=0,j=chars.length-1;           //用头尾两个指针比，只遍历一次
            while (i<j){
                if (chars[i]==chars[j]){
                    i++;
                    j--;
                }else {
                    return false;
                }
            }
            return true;
        }
    }
}
