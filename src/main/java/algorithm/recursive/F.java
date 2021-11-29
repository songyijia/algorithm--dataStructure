package algorithm.recursive;

import java.util.Stack;

/**
 * 斐波那契数列第N个数
 * 1 2 3 5 8 13 21
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

    public static void main(String[] args) {
        System.out.println(f(5));
        System.out.println(f2(5));
    }

    public static int f2(int N){
        Stack<Integer> stack = new Stack<>();
        stack.add(1);stack.add(2);
        for (int i = 3; i <= N ; i++) {
            Integer n1 = stack.pop();
            Integer n2 = stack.pop();
            stack.add(n1);
            stack.add(n1+n2);
        }
        if (N>2){
            return stack.pop();
        }
        return 0;
    }
}
