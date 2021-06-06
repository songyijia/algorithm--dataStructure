package algorithm.list;

public class MyStack<T> {
    private DoubleList<T> doubleList = new DoubleList<>();

    public DoubleList.Node push(T value){
        return this.doubleList.addFromHead(value);
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
        MyStack<String> myStack = new MyStack<String>();
        myStack.push("a");
        myStack.push("b");
        myStack.push("c");
        System.out.println(myStack);
        myStack.pop();
        System.out.println(myStack);
        myStack.pop();
        System.out.println(myStack);
        myStack.push("d");
        System.out.println(myStack);
        myStack.pop();
        System.out.println(myStack);

    }
}
