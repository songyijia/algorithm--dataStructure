package algorithm.tree;

/**
 * 求x为根，最大二叉搜索子树有多少节点
 * 1.跟x无关：
 *      左边子树是BST的最大节点，或右边是BST的最大节点。
 * 2.跟x有关：
 *      左边子树是BST，且右边是BST。且左边的Max < X,右边的 Min> X
 *
 *  【二叉搜索树：左子树小于根，右子树大于根】
 */
public class BinarySearchTree {
    static class Info{
        int maxSubBSTSize;
        boolean isAllBST;
        int max;
        int min;

        public Info(int maxSubBSTSize, boolean isAllBST, int max, int min) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.isAllBST = isAllBST;
            this.max = max;
            this.min = min;
        }
    }

    public static int getBST(RecuriseTravelBT.Node head){
        return process(head).maxSubBSTSize;
    }
    public static Info process(RecuriseTravelBT.Node head){
        if (head == null){
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = (int) head.value;
        int max = (int) head.value;
        int maxSubBSTSize = 0;
        if (leftInfo != null){
            min = Math.min(min,leftInfo.min);
            max = Math.max(max,leftInfo.max);
            maxSubBSTSize = Math.max(maxSubBSTSize,leftInfo.maxSubBSTSize);
        }
        if (rightInfo != null){
            min = Math.min(min,rightInfo.min);
            max = Math.max(max,rightInfo.max);
            maxSubBSTSize = Math.max(maxSubBSTSize,rightInfo.maxSubBSTSize);
        }
        boolean isAllBST = false;
        if ((leftInfo == null ? true : (leftInfo.isAllBST && leftInfo.max < (int)head.value
        && (rightInfo == null ? true : (rightInfo.isAllBST && rightInfo.min > (int)head.value))))){
            isAllBST = true;
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                    + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
        }
        return new Info(maxSubBSTSize,isAllBST,max,min);

    }
    public static void main(String[] args) {
        RecuriseTravelBT.Node node1 = new RecuriseTravelBT.Node(1);
        RecuriseTravelBT.Node node2 = new RecuriseTravelBT.Node(2);
        RecuriseTravelBT.Node node3 = new RecuriseTravelBT.Node(3);
        RecuriseTravelBT.Node node4 = new RecuriseTravelBT.Node(4);
        RecuriseTravelBT.Node node5 = new RecuriseTravelBT.Node(5);
        node4.left = node2;node4.right = node5;
        node2.left = node1;node2.right = node3;
        /**
         *       4
         *     2  5
         *   1 3
         */
        System.out.println(getBST(node2));
    }
}
