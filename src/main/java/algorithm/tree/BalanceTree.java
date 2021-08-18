package algorithm.tree;

/**
 * 二叉树递归套路
 * 1）假设以X节点为头，假设可以向X左树和右树要任何信息
 * 2、在上一步的假设下，讨论以x为头节点的树，得到答案的可能性（最重要）
 * 3、列出所有可能性后，确定到底需要向左树和右树要什么样的信息
 * 4、把左树信息和右树信息求全集，就是任何一颗子树都需要返回的信息
 * 5、递归函数都返回s,每一颗子树都这么要求
 * 6、写代码，在代码中考虑如何把左树的信息和右树信息整合出整棵树的信息
 *
 * 【判断树是否为平衡二叉树：又被称为AVL树（有别于AVL算法），且具有以下性质：它是一 棵空树或它的左右两个子树的高度差的绝对值不超过1，
 * 并且左右两个子树都是一棵平衡二叉树。这个方案很好的解决了二叉查找树退化成链表的问题，把插入，查找，删除的时间复杂度最好情况和最坏情况都维持在O(logN)。
 * 但是频繁旋转会使插入和删除牺牲掉O(logN)左右的时间，不过相对二叉查找树来说，时间上稳定了很多。

 】
 */
public class BalanceTree {
    static class Info{
        boolean isBalanced;
        int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean isBalance2(RecuriseTravelBT.Node head){
        return process2(head).isBalanced;
    }

    private static Info process2(RecuriseTravelBT.Node head) {
        if (head==null){
            return new Info(true,0);
        }
        Info leftInfo = process2(head.left);
        Info rightInfo = process2(head.right);
        int height = Math.max(leftInfo.height,rightInfo.height)+1;
        boolean isBalanced = true;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height)>1){
            isBalanced = false;
        }
        return new Info(isBalanced,height);
    }

    public static void main(String[] args) {
        RecuriseTravelBT.Node node1 = new RecuriseTravelBT.Node(1);
        RecuriseTravelBT.Node node2 = new RecuriseTravelBT.Node(2);
        RecuriseTravelBT.Node node3 = new RecuriseTravelBT.Node(3);
        RecuriseTravelBT.Node node4 = new RecuriseTravelBT.Node(4);
        RecuriseTravelBT.Node node5 = new RecuriseTravelBT.Node(5);
        node1.left = node2;node1.right = node3;
        node2.left = node4;node2.right = node5;
        System.out.println(isBalance2(node1));
    }
}
