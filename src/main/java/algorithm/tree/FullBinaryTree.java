package algorithm.tree;

import java.util.LinkedList;

/**
 * 判断树是否为满二叉树
 *  高h 的树 ，节点数为 2^(h-1)-1
 */
public class FullBinaryTree {
    public static boolean isFullBT(RecuriseTravelBT.Node head){
        if (head == null){
            return false;
        }
        FullInfo all = process(head);
        return (1<<all.height) - 1 == all.nodes;
    }


    private static FullInfo process(RecuriseTravelBT.Node head) {
        if (head == null){
            return new FullInfo(0,0);
        }
        FullInfo leftInfo = process(head.left);
        FullInfo rightInfo = process(head.right);
        int height = Math.max(leftInfo.height,rightInfo.height)+1;
        int nodes = leftInfo.nodes+rightInfo.nodes+1;
        return new FullInfo(height,nodes);
    }

    private static class FullInfo{
        int height;
        int nodes;

        public FullInfo(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static void main(String[] args) {
        RecuriseTravelBT.Node node1 = new RecuriseTravelBT.Node(1);
        RecuriseTravelBT.Node node2 = new RecuriseTravelBT.Node(2);
        RecuriseTravelBT.Node node3 = new RecuriseTravelBT.Node(3);
        RecuriseTravelBT.Node node4 = new RecuriseTravelBT.Node(4);
        RecuriseTravelBT.Node node5 = new RecuriseTravelBT.Node(5);
        node4.left = node2;node4.right = node5;
        node2.left = node1;node2.right = node3;
        System.out.println(isFullBT(node4));
    }
}
