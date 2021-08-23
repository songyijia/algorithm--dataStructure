package algorithm.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 图的宽度优先遍历：所谓宽度优先，即按层遍历，利用队列的先进先出，将每层的元素有序放入队列，并从队列出队，即可遍历完成
 */
public class BFS {
    //从node出发，进行宽度优先遍历
    public static void bfs(Node node){
        if (node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

    /**
     * 深度优先遍历
     * @param node
     */
    public static void dfs(Node node){
        if (node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    //只遍历一条线便跳出循环，继续从栈中取
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[][] matrix = new Integer[7][3];
        matrix[0] = new Integer[]{1,1,3};
        matrix[1] = new Integer[]{4,1,2};
        matrix[2] = new Integer[]{3,1,4};
        matrix[3] = new Integer[]{2,2,5};
        matrix[4] = new Integer[]{7,3,5};
        matrix[5] = new Integer[]{9,4,5};
        matrix[6] = new Integer[]{10,5,6};
        Graph graph = GraphGenerator.createGraph(matrix);
        System.out.println("宽度");
        kuandu(graph.nodes.get(1));
        System.out.println("深度");
        deepbianl(graph.nodes.get(1));
    }

    public static void kuandu(Node node){
        LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
//            if (!set.contains(cur)) {
//                System.out.println(cur.value);
//            }
            System.out.println(cur.value);

            if (!cur.nexts.isEmpty()){
                for (Node next : cur.nexts) {
                    if (!set.contains(next)){
                        queue.add(next);
                        set.add(next);
//                        System.out.println(next.value);
                    }
                }
            }
        }
    }

    public static void deepbianl(Node node){
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
