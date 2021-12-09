package algorithm.recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 序列：不连续。 子串：连续
 * 打印字符串所有子序列：将一个串完全展开，如：abc
 *         要a       不要a
 *      要b  不要b
 *   要c 不要c
 *   abc  ab  ac a  bc b c ''
 *
 */
public class PrintAllSubSequence {

    public static List<String> subs(String s){
        char[] chars = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(chars,0,ans,path);
        return ans;
    }

    /**
     * 这也是一个深度优先遍历，不用恢复现场，因为现场已经记录在JVM栈中
     * @param chars 不变
     * @param index 此时来到的位置， 要 or 不要
     * @param ans 如果index来到了chars中的终止位置，把沿途路径形成的答案放入ans
     * @param path 之前做出的选择
     */
    private static void process1(char[] chars, int index, List<String> ans, String path) {
        if (index == chars.length){
            ans.add(path);
            return;
        }
        String no = path;
        process1(chars,index+1,ans,no);
        String yes = path+String.valueOf(chars[index]);
        process1(chars,index+1,ans,yes);
    }

    public static List<String> subsNoRepeat(String s){
        char[] chars = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(chars,0,set,path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    private static void process2(char[] chars, int index, HashSet<String> set, String path) {
        if (index == chars.length){
            set.add(path);
            return;
        }
        String no = path;
        process2(chars,index+1,set,no);
        String yes = path+String.valueOf(chars[index]);
        process2(chars,index+1,set,yes);
    }

    public static void main(String[] args) {
        String s = "abc";
        List<String> subs = subs(s);
        System.out.println(subs);
        List<String> aabbcc = subsNoRepeat("aabbcc");
        System.out.println(aabbcc);
    }
}
