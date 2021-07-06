package algorithm.list;

/**
 * 双向链表 用于实现栈和队列
 */
public class DoubleList<T> {
    Node head;
    Node tail;
    static class Node<T>{
        Node pre;
        Node next;
        T value;

        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * 从头部入队
     * @param value
     * @return
     */
    public Node addFromHead(T value){
        Node node = new Node(value);
        if (head == null && tail == null){
            head = node;
            tail = node;
        }else {
            node.next = head;
            head.pre = node;
            head = node;
        }
        return head;
    }
    /**
     * 从头出队
     */
    public Node popFromHead(){
        if (head == null){
            return null;
        }
        Node<T> cur = head;
        if (head == tail){
            head = tail = null;
        }else {
            head = head.next;
            cur.next = null;
            head.pre = null;
        }
        return cur;
    }
    /**
     * 从尾部入队
     */
    public Node addFromTail(T value){
        Node node = new Node(value);
        if (head == null && tail == null){
            head = node;
            tail = node;
        }else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
        return tail;
    }
    /**
     * 从尾部出队
     */
    public Node popFromTail(){
        if (head == null){
            return null;
        }
        Node<T> cur = tail;
        if (head == tail){
            head = tail = null;
        }else {
            tail = tail.pre;
            cur.pre = null;
            tail.next = null;
        }
        return cur;
    }

    public void printList(){
        Node cur = head;
        while (cur != null){
            System.out.print(cur.value+"   ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node7 = new Node<>(7);
        DoubleList<Integer> doubleList = new DoubleList<Integer>();
        doubleList.addFromHead(1);
        doubleList.printList();
        doubleList.addFromHead(2);
        doubleList.printList();
        doubleList.addFromTail(3);
        doubleList.printList();
        doubleList.popFromHead();
        doubleList.printList();
        doubleList.addFromTail(4);
        doubleList.addFromHead(5);
        doubleList.printList();
        doubleList.popFromTail();
        doubleList.addFromHead(6);
        doubleList.addFromTail(7);
        doubleList.printList();
    }
}
