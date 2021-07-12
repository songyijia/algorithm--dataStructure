package algorithm.tree;

/**
 * 找任意树节点的后继节点(后继节点为中序遍历的下一个节点)
 */
public class GetNextTreeNode {
    static class Node{
        Object value;
        Node left;
        Node right;
        Node parent;

        public Node(Object value) {
            this.value = value;
        }
    }

    public static Node getSuccessorNode(Node node){
        if (node == null){
            return node;
        }
        if (node.right != null){
            return getLeftMost(node.right);
        }else {
            Node parent = node.parent;
            while (parent != null && parent.right == node){
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private static Node getLeftMost(Node node) {
        if (node == null){
            return node;
        }
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

}
