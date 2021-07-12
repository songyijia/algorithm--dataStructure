package algorithm.tree;

import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import static algorithm.tree.RecuriseTravelBT.in;

/**
 * 非递归遍历二叉树
 */
public class UnRecuriseTravelBT {
    /**
     * 先序：1.先打印出栈，2.有右节点，先压右节点，3，再压左节点
     * @param head
     */
    public static void pre(RecuriseTravelBT.Node head){
        if (head == null){
            return;
        }
        Stack<RecuriseTravelBT.Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
            head = stack.pop();
            System.out.println(head.value);
            if (head.right != null){
                stack.push(head.right);
            }
            if (head.left != null){
                stack.push(head.left);
            }

        }
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
        System.out.println("先序：");
        pre(node1);
        System.out.println("中序：");
        in(node1);
        System.out.println("后序：");
        post(node1);
        System.out.println("层序：");
        level(node1);
        System.out.println("层数最多："+levelTreeMax(node1));
        System.out.println("层数最多："+levelTreeMaxNoMap(node1));

    }

    /**
     * 1.整个左边界入栈，2，再弹出，3，再处理右子树
     * @param head
     */
    private static void in(RecuriseTravelBT.Node head) {
        if (head != null) {
            Stack<RecuriseTravelBT.Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value+" ");
                    head = head.right;
                }

            }
        }
    }

    private static void post(RecuriseTravelBT.Node head) {
        Stack<RecuriseTravelBT.Node> stack = new Stack<>();
        Stack<RecuriseTravelBT.Node> stack2 = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
            head = stack.pop();
            stack2.push(head);
            if (head.left != null){
                stack.push(head.left);
            }
            if (head.right != null){
                stack.push(head.right);
            }
        }
        while (!stack2.isEmpty()){
            RecuriseTravelBT.Node node = stack2.pop();
            System.out.println(node.value);
        }
    }

    public static void post2(RecuriseTravelBT.Node head){
        if (head != null){
            Stack<RecuriseTravelBT.Node> stack = new Stack<RecuriseTravelBT.Node>();
            stack.push(head);
            RecuriseTravelBT.Node c = null;
            while (!stack.isEmpty()){
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right){
                    stack.push(c.left);
                } else if (c.right != null && head != c.right){
                    stack.push(c.right);
                } else {
                    System.out.println(stack.pop().value);
                    head = c;
                }
            }

        }
    }

    public static void level(RecuriseTravelBT.Node head){
        if (head != null){
            Queue<RecuriseTravelBT.Node> queue = new LinkedBlockingQueue<>();
            queue.add(head);
            while (!queue.isEmpty()){
                head = queue.poll();
                System.out.println(head.value);
                if (head.left != null){
                    queue.add(head.left);
                }
                if (head.right != null) {
                    queue.add(head.right);
                }
            }
        }
    }

    public static int levelTreeMax(RecuriseTravelBT.Node head){
        HashMap<RecuriseTravelBT.Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head,1);
        int curLevel = 1,curLevelNodes = 0,max = 0;
        Queue<RecuriseTravelBT.Node> queue = new LinkedBlockingQueue<>();
        queue.add(head);
        while (!queue.isEmpty()){
            RecuriseTravelBT.Node cur = queue.poll();
            Integer curNodeLevel = levelMap.get(cur);
            if (cur.left != null){
                levelMap.put(cur.left,curNodeLevel+1);
                queue.add(cur.left);
            }
            if (cur.right != null){
                levelMap.put(cur.right,curNodeLevel+1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel){
                curLevelNodes++;
            } else {
                //下一层
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);//最后一层
        return max;

    }

    public static int levelTreeMaxNoMap(RecuriseTravelBT.Node head){
        int curLevel = 1,curLevelNodes = 0,max = 0;
        RecuriseTravelBT.Node curEnd = head;
        RecuriseTravelBT.Node nextEnd = null;
        Queue<RecuriseTravelBT.Node> queue = new LinkedBlockingQueue<>();
        queue.add(head);
        while (!queue.isEmpty()){
            RecuriseTravelBT.Node cur = queue.poll();
            if (cur.left != null){
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null){
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (curEnd == cur){
                max = Math.max(max,curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;

    }
}
