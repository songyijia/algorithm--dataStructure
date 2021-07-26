package algorithm.recursive;

/**
 * 从左往右的尝试
 * 规定1和A对应，2和B对应，3和C对应，那么111可以转化为“AAA","KA"和"AK"
 * 给定一个只有数字字符的字符串str,返回有多少种转化结果
 */
public class Number2Char {
    public static int number2Char(String str){
        if (str.length()==0){
            return 0;
        }
        char[] chars = str.toCharArray();
        return process(chars,0);
    }

    private static int process(char[] str, int i) {
        if (str[i]=='1'){
            return 1;
        }
        // i到终止位置
        if (str[i]=='0'){
            return 0;
        }
        //i没到终止位置，str[i]字符不是‘0’
        if (str[i] == '1'){
            int res = process(str,i+1);//i自己作为单独部分，后续有多少种方式
            if (i+1 < str.length) {
                res+=process(str,i+2);//（i和i+1）作为单独部分，后续有多少种方式
            }
            return res;
        }
        if (str[i] == '2'){
            int res = process(str,i+1);
            if (i+1 <str.length &&(str[i] >= '0' && str[i] <= '6')){
                res+=process(str,i+2);
            }
            return res;
        }
        // str[i] == '3 ~ 9'
        return process(str,i+1);
    }

}
