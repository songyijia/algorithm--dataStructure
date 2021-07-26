package algorithm.recursive;

import java.util.Stack;

/**
 * 不申请多的内存，将stack逆序
 */
public class ReverseStack {
    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int i = f(stack);
        System.out.println(i);
        reverse(stack);
        stack.push(i);
    }

    //return stack's endest value
    private static int f(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()){
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop()+"  ");
        }
    }
}
