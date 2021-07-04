package algorithm.listtest;

import java.util.ArrayList;

public class FastSlow {
    public static class Node{
        int value;
        Node next;
        Node(int v){
            value = v;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.next = node2;node2.next = node3;node3.next = node4;node4.next = node5;
        System.out.println(midOrUpMidNode(node1).value);
        System.out.println(midOrDownMidNode(node1).value);
        System.out.println(midOrUpMidPreNode(node1).value);
        System.out.println(midOrDownMidNextNode(node1).value);
    }

    /**
     * 给定链表头节点，奇数返回中点，偶数返回上中点
     * @param head
     * @return
     */
    public static Node midOrUpMidNode(Node head){
        if (head == null || head.next == null || head.next.next== null){
            return head;
        }
        //有三个以上的node
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next!=null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    /**
     * 给定链表头节点，奇数返回中点，偶数返回下中点
     * @param head
     * @return
     */
    public static Node midOrDownMidNode(Node head){
        if (head == null || head.next == null ){
            return head;
        }
        //有2个以上的node
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next!=null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 给定链表头节点，奇数返回中点前一个点，偶数返回上中点前一个点
     * @param head
     * @return
     */
    public static Node midOrUpMidPreNode(Node head){
        if (head == null || head.next == null || head.next.next== null ){
            return null;
        }
        //三个以上点

        Node slow = head;
        Node fast = head.next.next;
        while (fast.next!=null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 给定链表头节点，奇数返回中点的下一个点，偶数返回下中点的下一个点
     * @param head
     * @return
     */
    public static Node midOrDownMidNextNode(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        //有3个以上的node
        Node slow = head.next.next;
        Node fast = head.next.next;
        while (fast.next!=null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node right1(Node head){
        if (head == null){
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur!= null){
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size()-1)/2);
    }

    public static Node right2(Node head){
        if (head == null){
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur!= null){
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size())/2);
    }

    public static Node right3(Node head){
        if (head == null||head.next==null||head.next.next==null){
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur!= null){
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size()-3)/2);
    }

    public static Node right4(Node head){
        if (head == null||head.next==null){
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur!= null){
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size()-2)/2);
    }
}
