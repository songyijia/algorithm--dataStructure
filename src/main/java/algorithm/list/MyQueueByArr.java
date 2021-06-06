package algorithm.list;

import javax.naming.OperationNotSupportedException;

/**
 * 队列数组实现
 */
public class MyQueueByArr<T> {
    Object[] elments;
    int pullIndex;
    int pushIndex;
    int size;
    int limit;

    public MyQueueByArr(int limit) {
        this.limit = limit;
        elments = new Object[limit];
    }

    public void push(T element) throws OperationNotSupportedException {
        if (size==limit){
            throw new OperationNotSupportedException("队列已满");
        }
        elments[nextIndex(pushIndex)] = element;
        size++;
    }

    public Object pop() throws OperationNotSupportedException {
        if (size==0){
            throw new OperationNotSupportedException("队列已满");
        }
        int index = nextPullIndex();
        Object elment = elments[index];
        size--;
        elments[index] = null;
        return elment;
    }

    private int nextIndex(int index) {
        if (size==0 || index+1 == limit ) {
            pushIndex = 0;
        } else if (size < limit){
            pushIndex++;
        }
        return pushIndex;
    }

    private int nextPullIndex(){
        if (size==limit){
            pullIndex = 0;
        }else {
            pullIndex++;
        }
        return pullIndex;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < elments.length; i++) {
            if (elments[i]==null){
                stringBuilder.append("  ");
            } else {
                stringBuilder.append(elments[i]);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws OperationNotSupportedException {
        MyQueueByArr<String> myQueue = new MyQueueByArr<String>(3);
        myQueue.push("a");
        myQueue.push("b");
        myQueue.push("c");
        System.out.println(myQueue);
        myQueue.pop();
        System.out.println(myQueue);
        myQueue.pop();
        System.out.println(myQueue);
        myQueue.push("d");
        System.out.println(myQueue);
        myQueue.pop();
        System.out.println(myQueue);

    }
}
