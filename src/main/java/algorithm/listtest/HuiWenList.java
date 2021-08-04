package algorithm.listtest;

import java.util.Stack;

public class HuiWenList {
    /**
     * 判断是否是回文链表
     * 利用栈将链表倒叙后进行比较
     * @param head
     * @return
     */
    public static boolean isHuiWenList1(FastSlow.Node head){
        Stack<FastSlow.Node> stack = new Stack<>();
        FastSlow.Node cur = head;
        while (cur != null){
            stack.add(cur);
            cur = cur.next;
        }
        while (head != null){
            if (head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //need n/2 extra space

    /**
     * 利用快慢指针原理，找到中点，节省一半空间
     * @param head
     * @return
     */
    public static boolean isHuiWenList2(FastSlow.Node head){
        if (head == null || head.next == null){
            return true;
        }
        FastSlow.Node right = head.next;
        FastSlow.Node cur = head;
        while (cur.next != null && cur.next.next != null){
            right = right.next;
            cur = cur.next.next;
        }
        Stack<FastSlow.Node> stack = new Stack<>();
        while (right != null){
            stack.push(right);
            right = right.next;
        }
        while (!stack.isEmpty()){
            if (head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // need O(1) extra space
    public static boolean isHuiWenList3(FastSlow.Node head){
        if (head == null || head.next == null){
            return true;
        }
        FastSlow.Node n1 = head;
        FastSlow.Node n2 = head;
        //find mid node
        while (n2.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next; //n2 -> right part first node
        n1.next = null;
        FastSlow.Node n3 = null;
        //right part convert
        while (n2 != null){
            n3 = n2.next; //n3 -> save next node
            n2.next = n1; // next of right node convert
            n1 = n2; // n1 move
            n2 = n3; // n2 move
        }
        n3 = n1; // n3 -> save last node
        n2 = head; // n2 -> left first node
        boolean res = true;
        while (n1 != null && n2 != null){
            if (n1.value != n2.value){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;
        n3.next = null;
        //recover list
        while (n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
}
