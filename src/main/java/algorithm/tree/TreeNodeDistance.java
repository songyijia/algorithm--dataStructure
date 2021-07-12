package algorithm.tree;

/**
 * 求树上两点间最大距离
 * 1)与X无关的 左右子树最大距离
 * 2）与X有关的，左右高度和
 */
public class TreeNodeDistance {
    static class Info{
        int maxDistance;
        int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static int maxDistance(RecuriseTravelBT.Node head){
        return process(head).maxDistance;
    }

    private static Info process(RecuriseTravelBT.Node x) {
        if (x == null){
            return new Info(0,0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance,rightInfo.maxDistance),
                leftInfo.height+rightInfo.height+1);
        return new Info(maxDistance,height);
    }
}
