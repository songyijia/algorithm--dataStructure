package algorithm.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 任意两节点的公共祖先
 */
public class PublicParent {

    public static RecuriseTravelBT.Node getPublicParent(RecuriseTravelBT.Node head, RecuriseTravelBT.Node o1, RecuriseTravelBT.Node o2) {
        //key的父节点是value
        Map<RecuriseTravelBT.Node, RecuriseTravelBT.Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<RecuriseTravelBT.Node> o1Set = new HashSet<>();
        RecuriseTravelBT.Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    private static void fillParentMap(RecuriseTravelBT.Node head, Map<RecuriseTravelBT.Node, RecuriseTravelBT.Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    private static class PInfo {
        RecuriseTravelBT.Node ans;
        boolean findO1;
        boolean findO2;

        public PInfo(RecuriseTravelBT.Node ans, boolean findO1, boolean findO2) {
            this.ans = ans;
            this.findO1 = findO1;
            this.findO2 = findO2;
        }
    }

    public static RecuriseTravelBT.Node getPublicParent2(RecuriseTravelBT.Node head, RecuriseTravelBT.Node o1, RecuriseTravelBT.Node o2) {
        return process(head, o1, o2).ans;
    }

    private static PInfo process(RecuriseTravelBT.Node head, RecuriseTravelBT.Node o1, RecuriseTravelBT.Node o2) {
        if (head == null) {
            return new PInfo(null, false, false);
        }
        PInfo leftInfo = process(head.left, o1, o2);
        PInfo rightInfo = process(head.right, o1, o2);

        boolean findO1 = head == o1 || leftInfo.findO1 || rightInfo.findO1;
        boolean findO2 = head == o2 || leftInfo.findO2 || rightInfo.findO2;

        //O1 和O2 最初的交汇点在哪？
        /**
         * 1，在左树上提前交汇了
         * 2，在右树上提前交汇了
         * 3，没有在左树或右树提前交汇，o1 o2 全了
         */
        RecuriseTravelBT.Node ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        }
        if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        }
        if (ans==null){
            if (findO1 && findO2){
                ans = head;
            }
        }
        return new PInfo(ans, findO1, findO2);

    }

    public static void main(String[] args) {
        RecuriseTravelBT.Node node1 = new RecuriseTravelBT.Node(1);
        RecuriseTravelBT.Node node2 = new RecuriseTravelBT.Node(2);
        RecuriseTravelBT.Node node3 = new RecuriseTravelBT.Node(3);
        RecuriseTravelBT.Node node4 = new RecuriseTravelBT.Node(4);
        RecuriseTravelBT.Node node5 = new RecuriseTravelBT.Node(5);
        node4.left = node2;
        node4.right = node5;
        node2.left = node1;
        node2.right = node3;
        System.out.println(getPublicParent2(node4,node1,node2));
    }
}
