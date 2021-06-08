package algorithm.list;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 用两个队列伪装成栈
 */
public class TwoQueueImplmentStack {
    private LinkedBlockingQueue queue = new LinkedBlockingQueue();
    private LinkedBlockingQueue helpQueue = new LinkedBlockingQueue();

    public void push(Object data){
        queue.add(data);
//        pushToPop();
    }

    public Object pop(){
        pushToPop();
        Object data = queue.remove();
        //交换两个队列，继续之前的逻辑
        LinkedBlockingQueue temp = queue;
        queue = helpQueue;
        helpQueue = temp;
        return data;
    }

    private void pushToPop() {
        while (queue.size()>1){
            helpQueue.add(queue.remove());
        }
    }

    private void popToPush() {
        while (helpQueue.size()>0){
            queue.add(helpQueue.remove());
        }
    }


    public static void main(String[] args) {
        TwoQueueImplmentStack stack = new TwoQueueImplmentStack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push("d");
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }
}
