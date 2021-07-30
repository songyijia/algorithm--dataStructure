package algorithm.recursive;

import java.util.HashMap;

/**
 * 剪纸拼目标字符串
 * “aabbccdd"  {"ab","ac","abc}
 */
public class StickersToSpellWord {
    public static int minStickers1(String[] stickers,String target){
        int n = stickers.length;
        int[][] map = new int[n][26]; //每个剪纸用一个长度为26的一维数组表示其个数
        HashMap<String,Integer> dp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                map[i][c-'a']++;
            }
        }
        dp.put("",0);
        return process1(dp,map,target);
    }

    private static int process1(HashMap<String, Integer> dp, int[][] map, String rest) {
        if (dp.containsKey(rest)){
            return dp.get(rest);
        }
        //以下就是正式的递归调用过程
        int ans = Integer.MAX_VALUE;// ans -> 搞定rest,使用最少的粘纸数量
        int n = map.length;// N种粘纸
        int[] tmap = new int[26]; // tmap去替代rest
        char[] target = rest.toCharArray();
        for (char c : target) {
            tmap[c-'a']++;
        }
        for (int i = 0; i < n; i++) {
            //target第一个字符，当前的剪纸有没有
            if (map[i][target[0]-'a'] == 0){ //防止递归跑不完 ，（剪纸的第一个字符消灭不了目标字符串的第一个字符）
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (tmap[j]>0){
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
                        sb.append((char)('a'+j));
                    }
                }
            }
            String s = sb.toString();
            int tmp = process1(dp, map, s); //每次将所有剪纸依次尝试 （base case返回的是0+1=1）
            if (tmp != -1){
                ans = Math.min(ans,1+tmp); //每次选减的最多的那个
            }
        }
        System.out.println("rest="+rest+", ans="+ans);
        dp.put(rest,ans==Integer.MAX_VALUE?-1:ans);//记录子任务结果
        return dp.get(rest);
    }

    public static void main(String[] args) {
        String target = "abbccddaaabbb";
        String[] arr = new String[]{"aa","ab","bccc","de"};
        System.out.println(minStickers1(arr,target));
    }
}
