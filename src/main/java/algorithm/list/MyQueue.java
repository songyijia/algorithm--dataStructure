package algorithm.list;

public class MyQueue<T> {
    private DoubleList<T> doubleList = new DoubleList<>();

    public DoubleList.Node push(T value){
        return this.doubleList.addFromTail(value);
    }

    public DoubleList.Node pop(){
        return this.doubleList.popFromHead();
    }

    @Override
    public String toString() {
        doubleList.printList();
        return "";
    }

    public static void main(String[] args) {
        MyQueue<String> myQueue = new MyQueue<String>();
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
