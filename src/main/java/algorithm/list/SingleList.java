package algorithm.list;

public class SingleList {
    static class Node {
        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
        }
    }
    static class DoubleNode {
        Object value;
        DoubleNode pre;
        DoubleNode next;

        public DoubleNode(Object value) {
            this.value = value;
        }
    }

    /**
     * 反转单链表
     * a->b->c->null
     * null<-a<-b<-c
     * @param head
     * @return
     */
    public static Node reserveList(Node head){
        Node pre = null;
        Node next = null;
//        while (head != null && head.next != null){ head.next由指向倒回去了
        while (head != null){
            next = head.next;
            head.next = pre;
//            next.next = head; 不能指倒回去，一次只动一个指针
            pre = head;
            head = next;
        }
        return pre;

    }

    /**
     * 反转双向链表
     * @param a
     * @return
     */
    private static DoubleNode reserveList(DoubleNode a) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (a !=null){
            next = a.next;
            a.pre = next;
            a.next = pre;
            pre = a;
            a = next;
        }
        return pre;
    }

    /**
     * 定值删除
     * @param head
     */
    public static Node removeNode(Node head,int num){
        //找第一个不是num的头
        while (head!=null){
            if ((int)head.value != num){
                break;
            }
            head = head.next;
        }
        Node pre = null;
        Node cur = head;
        //删掉后面的num
        while (cur!=null){
            if ((int)cur.value == num) {
                pre.next = cur.next;
                //删掉cur，不移动pre
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void printList(Node head){
        while (head != null){
            System.out.print(head.value+"   ");
            head = head.next;
        }
        System.out.println();
    }
   public static void printList(DoubleNode head){
        while (head != null){
            System.out.print(head.value+"   ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

//        testReserveDouble();
        testRemoveNum();
    }

    public static void testReserveSingle(){
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        a.next = b;b.next=c;
        printList(a);
        Node head = reserveList(a);
        printList(head);
    }

    public static void testReserveDouble(){
        DoubleNode a = new DoubleNode("a");
        DoubleNode b = new DoubleNode("b");
        DoubleNode c = new DoubleNode("c");
        a.next = b;b.pre = a;b.next=c;c.pre=b;
        printList(a);
        DoubleNode head = reserveList(a);
        printList(head);
    }

    public static void testRemoveNum(){
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(1);
        Node node4 = new Node(3);
        Node node5 = new Node(2);
        node1.next = node2;node2.next = node3;node3.next = node4;node4.next = node5;
        printList(node1);
        Node head = removeNode(node1,1);
        printList(head);
    }


}
