package algorithm.list;

import javax.naming.OperationNotSupportedException;

public class MyQueueByArr2 {
    Object[] elments;
    int pullIndex;
    int pushIndex;
    int size;
    final int limit;

    public MyQueueByArr2(int limit) {
        this.elments = new Object[limit];
        this.pullIndex = 0;
        this.pushIndex = 0;
        this.size = 0;
        this.limit = limit;
    }

    public void push(Object element) throws OperationNotSupportedException {
        if (size==limit){
            throw new OperationNotSupportedException("队列已满");
        }
        elments[pushIndex] = element;
        size++;
        pushIndex = nextIndex(pushIndex);
    }

    public Object pop() throws OperationNotSupportedException {
        if (size==0){
            throw new OperationNotSupportedException("队列已满");
        }
        Object elment = elments[pullIndex];
        size--;
        elments[pullIndex] = null;
        pullIndex = nextIndex(pullIndex);
        return elment;
    }

    private int nextIndex(int index) {
        return index < limit-1?index+1:0;
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
        MyQueueByArr2 myQueue = new MyQueueByArr2(3);
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
