package algorithm.recursive;

/**
 * 斐波那契数列
 */
public class F {
    public static int f(int N){
        if (N==1){
            return 1;
        }
        if (N==2){
            return 2;
        }
        return f(N-1)+f(N-2);
    }
}
