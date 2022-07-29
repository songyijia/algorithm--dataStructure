package algorithm.listtest;

public class ReverseKGroupList {
    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int value) {
            this.val = value;
        }
    }
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;node2.next = node3;node3.next=node4;node4.next = node5;
        ListNode head = reverseKGroupList(node1,2);
        while (head != null){
            System.out.print(head.val+" ,");
            head = head.next;
        }
    }

    private static ListNode reverseKGroupList(ListNode head, int k) {
        if (head == null){
            return head;
        }
        int count  = 0;
        ListNode cur = head;
        while (count!=k & cur.next != null){
            cur = cur.next;
            count++;
        }
        if (count == k){
            cur = reverseKGroupList(cur,k);
            while (count-->0){
                ListNode tmp = head.next;
                head.next = cur;
                cur = head;
                head = tmp;
            }
            head = cur;
        }
        return head;
    }
}
