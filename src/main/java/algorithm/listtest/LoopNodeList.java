package algorithm.listtest;

/**
 * 两个可能有环也可能无环的单链表，头节点head1和head2.
 * 实现：如果两个链表相交，返回相交的第一个节点，如不相交，返回null
 */
public class LoopNodeList {

    //找到链表第一个入环节点，如果无环，返回null,有环返回第一个入环节点
    public static CopyList.Node getLoopNode(CopyList.Node head){
        if (head == null || head.next == null){
            return null;
        }
        CopyList.Node n1 = head.next;
        CopyList.Node n2 = head.next.next;
        while (n1 != n2){
            if (n2.next == null || n2.next.next == null){
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head;
        while (n1 != n2){
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    //如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
    public static CopyList.Node noLoop(CopyList.Node head1, CopyList.Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        CopyList.Node cur1 = head1;
        CopyList.Node cur2 = head2;
        int n = 0;
        while (cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        // n : 链表1长度减去链表2 长度的值
        //把长的给cur1,短的给cur2
        cur1 = n >0 ? head1 :  head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0){
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;

    }
    public static CopyList.Node bothLoop(CopyList.Node head1, CopyList.Node loop1, CopyList.Node head2, CopyList.Node loop2){
        CopyList.Node cur1 = null,cur2 = null;
        if (loop1 == loop2){
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            // n : 链表1长度减去链表2 长度的值
            //把长的给cur1,短的给cur2
            cur1 = n >0 ? head1 :  head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0){
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1){
                if (cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    /**
     * 获取两个链表的第一个交点
     * @param head1
     * @param head2
     * @return
     */
    public static CopyList.Node getIntersectNode(CopyList.Node head1, CopyList.Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        CopyList.Node loop1 = getLoopNode(head1);
        System.out.println("loop1:"+loop1.value);
        CopyList.Node loop2 = getLoopNode(head2);
        System.out.println("loop2:"+loop2.value);
        if (loop1 == null && loop2 == null){
            return noLoop(head1,head2);
        }
        if (loop1 != null && loop2 != null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
    }

    public static void main(String[] args) {
        CopyList.Node node1 = new CopyList.Node(1);
        CopyList.Node node2 = new CopyList.Node(2);
        CopyList.Node node3 = new CopyList.Node(3);
        CopyList.Node node4 = new CopyList.Node(4);
        CopyList.Node node5 = new CopyList.Node(5);
        CopyList.Node node6 = new CopyList.Node(6);
        CopyList.Node node7 = new CopyList.Node(7);
        CopyList.Node nodea = new CopyList.Node(8);
        CopyList.Node nodeb = new CopyList.Node(9);
        CopyList.Node nodec = new CopyList.Node(10);
        node1.next = node2;node2.next = node3;node3.next = node4;node4.next = node5;
        node5.next = node6;node6.next = node7;node7.next = node5;
        nodea.next = nodeb;nodeb.next = nodec;nodec.next = node6;
        System.out.println(getIntersectNode(node1,nodea).value);
    }
}
