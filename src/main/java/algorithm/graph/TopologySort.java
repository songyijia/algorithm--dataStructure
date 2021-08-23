package algorithm.graph;

import java.util.*;

/**
 * 拓扑排序
 */
public class TopologySort {

    //directed graph and no loop
    public static List<Node> sortedTopology(Graph graph){
        //key:某一个node
        // value: 剩余入度
        HashMap<Node,Integer> inMap = new HashMap<>();
        //剩余入度为0点，才能进这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node,node.in);
            if (node.in == 0){
                zeroInQueue.add(node);
            }
        }
        //拓扑排序的结果，依次加入result
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next,inMap.get(next)-1);
                if (inMap.get(next)==0){
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
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
        List<Node> nodes = sortedTopology(graph);
        System.out.println(nodes);
    }
}
