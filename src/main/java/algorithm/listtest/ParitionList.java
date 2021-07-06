package algorithm.listtest;

/**
 * 将链表分区
 */
public class ParitionList {
    public static FastSlow.Node listPartition1(FastSlow.Node head,int pivot){
        if (head == null){
            return head;
        }
        int i = 0;
        FastSlow.Node cur = head;
        while (cur != null){
            i++;
            cur = cur.next;
        }
        FastSlow.Node[] nodes = new FastSlow.Node[i];
        cur = head;
        for (i = 0; i < nodes.length; i++) {
            nodes[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodes,pivot);
        for (i = 1; i < nodes.length; i++) {
            nodes[i-1].next = nodes[i];
        }
        nodes[i-1].next = null;
        return nodes[0];
    }
    //  1 2 3  5 4
    private static void arrPartition(FastSlow.Node[] nodes, int pivot) {
        int less = -1;
        int more = nodes.length;
        int index = 0;
        while (index<more){
            if (nodes[index].value == pivot){
                index++;
            } else if (nodes[index].value < pivot){
                swap(nodes,index++,++less);
            } else if (nodes[index].value > pivot){
                swap(nodes,index,--more);
            }
        }
        //没用
//        swap(nodes,more,nodes.length-1); //为什么一定要换more 和 right ,more里面的数怎么有序的？？或者less里面怎么有序的？？不断拆分
    }

    private static void swap(FastSlow.Node[] nodes, int a, int b) {
        FastSlow.Node tmp = nodes[a];
        nodes[a] = nodes[b];
        nodes[b] = tmp;
    }

    /**
     * 用6个变量划分链表
     * @param head
     * @param privo
     */
    public static FastSlow.Node listPartition2(FastSlow.Node head, int privo){
        FastSlow.Node lh = null,lt = null,eh = null,et = null,bh=null,bt = null;
        FastSlow.Node cur = head;
        while (cur!= null){
            if (cur.value < privo){
                if (lh==null){
                    lh = lt = cur;
                } else {
                    lt.next = cur;
                    lt = cur;
                }
            } else if (cur.value == privo){
                if (eh==null){
                    eh = et = cur;
                } else {
                    et.next = cur;
                    et = cur;
                }
            } else {
                if (bh==null){
                    bh = bt = cur;
                } else {
                    bt.next = cur;
                    bt = cur;
                }
            }
            FastSlow.Node next = cur.next;
            cur.next = null;
            cur = next;
        }
//        lt.next = eh;
//        et.next = bh;
        // 小于区域的尾巴，连等于区域的头，等于区域的尾吧连大于区域的头
        if (lt != null){
            lt.next = eh;
            et = et == null ? lt:et;//谁去连大于区域的头
        }
        if (et!=null){
            et.next = lh;
        }
        return lh != null ? lh : (eh != null ? eh:lh);
    }
    public static void main(String[] args) {
        FastSlow.Node node1 = new FastSlow.Node(4);
        FastSlow.Node node2 = new FastSlow.Node(6);
        FastSlow.Node node3 = new FastSlow.Node(2);
        FastSlow.Node node4 = new FastSlow.Node(3);
        FastSlow.Node node5 = new FastSlow.Node(1);
        FastSlow.Node node6 = new FastSlow.Node(5);
        FastSlow.Node node7 = new FastSlow.Node(0);
        node1.next = node2;node2.next = node3;node3.next = node4;node4.next = node5;node5.next = node6;
        node6.next = node7;
        FastSlow.Node head = listPartition2(node1, 3);
        FastSlow.Node cur = head;
        while (cur!= null){
            System.out.println(cur.value);
            cur = cur.next;
        }


    }
}
