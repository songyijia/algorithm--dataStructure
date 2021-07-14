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
}
