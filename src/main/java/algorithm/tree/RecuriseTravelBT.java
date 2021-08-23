package algorithm.tree;

import java.util.HashSet;
import java.util.Stack;

/**
 * 递归的遍历二叉树
 */
public class RecuriseTravelBT {
    static class Node{
        Object value;
        Node left;
        Node right;

        public Node(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    /**
     * 先序遍历
     * @param head
     */
    public static void pre(Node head){
        if (head == null){
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }
    /**
     * 中序遍历
     * @param head
     */
    public static void in(Node head){
        if (head == null){
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    /**
     * 后序遍历
     * @param head
     */
    public static void post(Node head){
        if (head == null){
            return;
        }
        post(head.left);
        post(head.right);
        System.out.println(head.value);
    }

    /**
     * 深度遍历：无效！
     * @param node
     */
    public static void deep(Node node){
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            if (cur.left != null){
                if (!set.contains(cur.left)) {
                    stack.add(cur);
                    stack.add(cur.left);
                    set.add(cur.left);
                    System.out.println(cur.left);
                    continue;
                }
            }
            if (cur.right != null){
                if (!set.contains(cur.right)) {
                    stack.add(cur);
                    stack.add(cur.right);
                    set.add(cur.right);
                    System.out.println(cur.right);
                    continue;
                }
            }

        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        node5.left = node7;
//        System.out.println("先序：");
//        pre(node1);
//        System.out.println("中序：");
//        in(node1);
//        System.out.println("后序：");
//        post(node1);
        //没有深度遍历
        deep(node1);
    }
}
