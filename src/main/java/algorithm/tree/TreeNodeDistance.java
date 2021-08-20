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

    public static void main(String[] args) {
        RecuriseTravelBT.Node node1 = new RecuriseTravelBT.Node(1);
        RecuriseTravelBT.Node node2 = new RecuriseTravelBT.Node(2);
        RecuriseTravelBT.Node node3 = new RecuriseTravelBT.Node(3);
        RecuriseTravelBT.Node node4 = new RecuriseTravelBT.Node(4);
        RecuriseTravelBT.Node node5 = new RecuriseTravelBT.Node(5);
        RecuriseTravelBT.Node node6 = new RecuriseTravelBT.Node(6);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        System.out.println(maxDistance(node1));

    }
}
