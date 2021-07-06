package algorithm.listtest;

import java.util.HashMap;

/**
 * 克隆链表
 */
public class CopyList {
    static class Node{
        int value;
        Node next;
        Node rand;
        Node(int value){
            this.value = value;
        }
    }

    public static Node copyListWithRand1(Node head){
        HashMap<Node,Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null){
            map.put(cur,new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur!= null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head){
        Node cur = head;
        while (cur != null){
            Node next = cur.next;
            cur.next = new Node(cur.value); // 老节点指向克隆节点
            cur.next.next = next;        //克隆节点指向老的下一个节点
            cur = next;
        }
        //设置rand指针
        cur = head;
        while (cur != null){
            Node copyNode = cur.next;
            copyNode.rand = cur.rand != null ? cur.rand.next : null;
            cur = cur.next.next;
        }
        //split next指针
        cur = head;
        Node newHead = cur.next;
        while (cur != null){
            Node copyNode = cur.next;
            Node next = cur.next.next;
            cur.next = next;
            if (next != null) {
                copyNode.next = next.next;
            }
            cur = next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;node2.next = node3;node3.next = node4;
        node1.rand = node3;node2.rand = node4;node4.rand = node1;
        Node newHead = copyListWithRand2(node1);
        while (node1!= null){
            System.out.println(node1);
            node1 = node1.next;
        }
        while (newHead!=null){
            System.out.println(newHead);
            newHead = newHead.next;
        }
    }
}
