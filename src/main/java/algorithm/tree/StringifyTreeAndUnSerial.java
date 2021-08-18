package algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化及反序列化树：序列化时需将null填入树中，将树看作满二叉树。
 */
public class StringifyTreeAndUnSerial {
    public static Queue<Object> preSerial(RecuriseTravelBT.Node head){
        Queue<Object> ans = new LinkedList<>();
        pres(head,ans);
        return ans;
    }

    private static void pres(RecuriseTravelBT.Node head, Queue<Object> ans) {
        if (head == null){
            ans.add(null);
        } else {
            ans.add(head.value);
            pres(head.left,ans);
            pres(head.right,ans);
        }
    }
    //根据先序队列反序列化回树，并返回根节点
    public static RecuriseTravelBT.Node buildByPreQueue(Queue<Object> queue){
        if (queue == null || queue.size() == 0){
            return null;
        }
        return preb(queue);
    }

    private static RecuriseTravelBT.Node preb(Queue<Object> queue) {
        Object value = queue.poll();
        if (value == null){
            return null;
        }
        RecuriseTravelBT.Node head = new RecuriseTravelBT.Node(value);
        head.left = preb(queue);
        head.right = preb(queue);
        return head;
    }

    //宽度优先级序列化树
    public static Queue<Object> levelSerial(RecuriseTravelBT.Node head){
        Queue<Object> ans = new LinkedList<>();
        if (head == null){
            ans.add(null);
        } else {
            ans.add(head.value);
            Queue<RecuriseTravelBT.Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()){
                head = queue.poll();
                if (head.left != null){
                    ans.add(head.left.value);
                    queue.add(head.left);
                } else {
                    ans.add(null);
                }
                if (head.right != null){
                    ans.add(head.right.value);
                    queue.add(head.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    //宽度优先级反序列化
    public static RecuriseTravelBT.Node levelUnSerial(Queue<Object> list){
        RecuriseTravelBT.Node head = generateNode(list.poll());
        Queue<RecuriseTravelBT.Node> queue = new LinkedList<>();
        if (head != null){
            queue.add(head);
        }
        RecuriseTravelBT.Node node = null;
        while (!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNode(list.poll());
            node.right = generateNode(list.poll());
            if (node.left != null){
                queue.add(node.left);
            }
            if (node.right != null){
                queue.add(node.right);
            }
        }
        return head;
    }

    private static RecuriseTravelBT.Node generateNode(Object value) {
        if (value == null){
            return null;
        }
        return new RecuriseTravelBT.Node(value);
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
        Queue<Object> queue = preSerial(node1);
        RecuriseTravelBT.Node head = buildByPreQueue(queue);
        RecuriseTravelBT.in(head);

    }
}
