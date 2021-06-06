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
        return node;
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
        return node;
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
            cur.next = null;
            tail.pre = null;
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
}
