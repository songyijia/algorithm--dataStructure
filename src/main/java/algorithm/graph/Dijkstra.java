package algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// no negative weight
public class Dijkstra {
    /**
     * 从某一点，到其它点最小距离
     * @param from
     * @return
     */
    public static HashMap<Node,Integer> dijkstral(Node from){
        /**
         * 从from到所有点最小距离
         * key: 从from出发到达key
         * value: 从from 出发达到key的最小距离
         * 如果在表中，没有T的记录，含义是从from出发到T这个点的距离为正无穷
         */
        HashMap<Node,Integer> distanceMap = new HashMap<>();
        distanceMap.put(from,0);
        //已经求过距离的节点，存在selectNodes中，以后再也不碰
        HashSet<Node> selectedNodes = new HashSet<>();

        Node minNode = getMinDistanceAndUnselectedNode(distanceMap,selectedNodes);
        while (minNode != null){
            Integer distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode,distance+edge.weight);
                } else {
                    distanceMap.put(edge.to,Math.min(distanceMap.get(toNode),distance+edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap,selectedNodes);
        }
        return distanceMap;
    }

    /**
     * 从距离表中选取，没有选过且距离最短的节点。从最小的边的节点出发！
     * @param distanceMap
     * @param selectedNodes
     * @return
     */
    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance){
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    private static class NodeRecord{
        Node node;
        int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /**
     * 改进后的dijkstra算法，利用小根堆[每次从最小的边的节点开始找]
     * 从from 出发，所有from 能到达的节点，生成到达每个节点的最小路径并返回
     */
    public static HashMap<Node,Integer> dijkstra2(Node head,int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head,0);
        HashMap<Node,Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()){
            NodeRecord nodeRecord = nodeHeap.pop();
            Node cur = nodeRecord.node;
            int distance = nodeRecord.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to,edge.weight+distance);
            }
            result.put(cur,distance);
        }
        return result;
    }

    private static class NodeHeap {
        private Node[] nodes; //实际堆结构
        //key:某一node,value 上面数组中的位置
        private HashMap<Node,Integer> heapIndexMap;
        //key:某一节点， value: 从源节点出发到该节点的最小距离
        private HashMap<Node,Integer> distanceMap;
        private int size; //堆上有多少节点

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)){
                distanceMap.put(node,Math.min(distanceMap.get(node),distance));
                insertHeapify(node,heapIndexMap.get(node));
            }
            if (!isEntered(node)){
                nodes[size]=node;
                heapIndexMap.put(node,size);
                distanceMap.put(node,distance);
                insertHeapify(node,size++);
            }
        }

        private void insertHeapify(Node node, Integer index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index-1)/2])){
                swap(index,(index-1)/2);
                index = (index-1)/2;
            }
        }

        public boolean isEmpty() {
            return size==0;
        }

        private boolean isEntered(Node node){
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node){
            return isEntered(node)&&heapIndexMap.get(node)!=-1;
        }

        private void swap(int index1,int index2){
            heapIndexMap.put(nodes[index1],index2);
            heapIndexMap.put(nodes[index2],index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0,size-1);
            heapIndexMap.put(nodes[size-1],-1);
            distanceMap.remove(nodes[size-1]);
            nodes[size-1] = null;
            heapify(0,--size);
            return nodeRecord;
        }

        private void heapify(int index, int size) {
            int left = index*2+1;
            while (left < size){
                int smallest = left+1 < size && distanceMap.get(nodes[left+1]) < distanceMap.get(nodes[left])
                        ? left+1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index){
                    break;
                }
                swap(smallest,index);
                index = smallest;
                left = index*2+1;
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
        HashMap<Node, Integer> result = dijkstral(graph.nodes.get(1));
        HashMap<Node, Integer> result2 = dijkstra2(graph.nodes.get(1),6);
        System.out.println("z");
    }

}
