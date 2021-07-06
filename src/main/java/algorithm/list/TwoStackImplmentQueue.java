package algorithm.list;

import java.util.Stack;

public class TwoStackImplmentQueue {
    private Stack pushStack = new Stack();
    private Stack popStack = new Stack();

    public void push(Object data){
        pushStack.add(data);
        pushToPop();
    }

    public Object pop(){
        if (!pushStack.isEmpty()){
            pushToPop();
        }
        return popStack.pop();
    }

    /**
     * push栈一直为空，一有新元素进去，便被搞去pop栈等出队
     */
    private void pushToPop() {
        if (popStack.isEmpty()) { //pop栈为空时，倒数据过去
            while (!pushStack.isEmpty()) {
                popStack.add(pushStack.pop());
            }
        }
    }


    public static void main(String[] args) {
        TwoStackImplmentQueue queue = new TwoStackImplmentQueue();
        queue.push("a");
        queue.push("b");
        queue.push("c");
        System.out.println(queue);
        System.out.println(queue.pop());
        System.out.println(queue);
        System.out.println(queue.pop());
        System.out.println(queue);
        queue.push("d");
        System.out.println(queue);

    }
}
