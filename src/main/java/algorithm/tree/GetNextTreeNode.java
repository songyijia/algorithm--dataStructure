package algorithm.tree;

/**
 * 找任意树节点的后继节点(后继节点为中序遍历的下一个节点)
 */
public class GetNextTreeNode {
    static class Node{
        Object value;
        Node left;
        Node right;
        Node parent;  //要找到任意节点的后继节点，则必须有向上的指针

        public Node(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
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
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node4.left = node2;node4.right = node5;
        node2.left = node1;node2.right = node3;
        node1.parent = node2;node3.parent= node2;
        node2.parent=node4;node5.parent=node4;
        System.out.println(getSuccessorNode(node1));
    }
}
