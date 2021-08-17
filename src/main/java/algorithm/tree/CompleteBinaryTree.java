package algorithm.tree;

import java.util.LinkedList;

/**
 * 【完全二叉树：设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，
    第 h 层所有的结点都连续集中在最左边】
    【满二叉树：深度为k且有2^k-1个结点的二叉树称为满二叉树】
 */
public class CompleteBinaryTree {
    /**
     * 判断是否为完全二叉树
     * 【宽度优先遍历】
     * 1.有右节点，同时有左节点
     * 2.若节点左右孩子不双全，那后续遍历的节点均为叶子节点
     * @param head
     * @return
     */
    public static boolean isCBT(RecuriseTravelBT.Node head){
        if (head == null){
            return true;
        }
        LinkedList<RecuriseTravelBT.Node> queue = new LinkedList<>();
        //是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        RecuriseTravelBT.Node l = null,r = null;
        queue.add(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;
            //如果遇到了不双全的节点之后，又发现当前节点不是叶节点
            if ((leaf && !(l==null && r== null))
                    || (l==null && r!= null)){
                return false;
            }
            if (l != null){
                queue.add(l);
            }
            if (r != null){
                queue.add(r);
            }
            if (l == null || r == null){
                leaf = true;
            }
        }
        return true;
    }

    private static class CInfo{
        boolean isFull;
        boolean isCBT;
        int height;
        //对每一颗子树，是否满二叉树，是否是完全二叉树，高度
        public CInfo(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static boolean isCBT2(RecuriseTravelBT.Node head){
        if (head == null){
            return true;
        }
        return process(head).isCBT;
    }

    private static CInfo process(RecuriseTravelBT.Node head){
        if (head == null){
            return new CInfo(true,true,0);
        }
        CInfo leftInfo = process(head.left);
        CInfo rightInfo = process(head.right);

        int height = Math.max(leftInfo.height,rightInfo.height)+1;

        boolean isFull = leftInfo.isFull && rightInfo.isFull && (leftInfo.height == rightInfo.height);
        boolean isCBT = false;
        if (isFull){
            isCBT = true;
        } else {
            //head 为头的树，不满
            if (leftInfo.isCBT && rightInfo.isCBT){
                if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height+1){
                    isCBT = true;
                }
                if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height+1){
                    isCBT = true;
                }
                if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
                    isCBT = true;
                }
            }
        }
        return new CInfo(isFull,isCBT,height);
    }

    //for test
    public static RecuriseTravelBT.Node generateRandomBST(int maxLevel,int maxValue){
        return generate(1,maxLevel,maxValue);
    }

    private static RecuriseTravelBT.Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5){
            return null;
        }
        RecuriseTravelBT.Node head = new RecuriseTravelBT.Node((int)(Math.random()*maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            RecuriseTravelBT.Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT2(head)!=isCBT(head)){
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
