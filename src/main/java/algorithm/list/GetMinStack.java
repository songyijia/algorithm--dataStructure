package algorithm.list;

import java.util.Stack;

/**
 * 要能返回栈中最小值的栈
 */
public class GetMinStack {
    static class MyStack1{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int newNum){
            if (stackMin.isEmpty()){
                stackMin.push(newNum);
            } else if (newNum< getmin()){
                stackMin.push(newNum);
            } else {
                stackMin.push(getmin());
            }
            stackData.push(newNum);
        }

        public int pop(){
            if (stackData.isEmpty()){
                throw new RuntimeException("空栈!");
            }
            int value = stackData.pop();
            stackMin.pop();
            return value;
        }

        private int getmin() {
            return stackMin.peek();
        }


    }
}
