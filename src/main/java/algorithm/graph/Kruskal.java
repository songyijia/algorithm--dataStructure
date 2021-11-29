package algorithm.graph;

import java.util.*;

public class Kruskal {
    //union-Find set
    private static class UnionFind{
        private HashMap<Node,Node> fatherMap;
        private HashMap<Node,Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes){
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }

        private Node findFather(Node n){
            Stack<Node> path = new Stack<>();
            while (n != fatherMap.get(n)){
                path.add(n);
                n = fatherMap.get(n);
            }
            while (!path.isEmpty()){
                fatherMap.put(path.pop(),n);
            }
            return n;
        }

        public boolean isSameSet(Node a,Node b){
            return findFather(a) == findFather(b);
        }

        public void union(Node a,Node b){
            if (a==null||b==null){
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if (aDai != bDai){
                Integer aSetSize = sizeMap.get(aDai);
                Integer bSetSize = sizeMap.get(bDai);
                if (aSetSize <= bSetSize){
                    fatherMap.put(aDai,bDai);
                    sizeMap.put(bDai,aSetSize+bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai,aDai);
                    sizeMap.put(aDai,aSetSize+bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }

    /**
     * K 方法最小生成树  并查集+小根堆
     * 将边从小到大排列，将能使边两边的点形成同一集合的边收藏，直到将所有边遍历完，肯定能连接所有点，将收藏的边返回
     * @param graph
     * @return
     */
    public static Set<Edge> kruskalMST(Graph graph){
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from,edge.to)){
                //能将两个节点串联的边加入
                result.add(edge);
                unionFind.union(edge.from,edge.to);
            }
        }
        return result;
    }

    private static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight-o2.weight;
        }
    }

    /**
     * P算法 最小生成树   小根堆+Set
     * 遍历所有节点，记录所有边，将边放入小根堆，从小根堆中解锁边，如果解锁出新节点，则为有效边，
     * 并把新节点的新边再放入小根堆，等下次解锁，
     *
     */
    public static Set<Edge> primMST(Graph graph){
        //解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        //被解锁的点
        HashSet<Node> nodeSet = new HashSet<>();
        //已经考虑过的边不再重复考虑
        HashSet<Edge> edgeSet = new HashSet<>();
        Set<Edge> result = new HashSet<>();//依次挑选的边在result里
        for (Node node : graph.nodes.values()) {
            //node 是开始点
            if (!nodeSet.contains(node)){
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    if (!edgeSet.contains(edge)){
                        edgeSet.add(edge);
                        priorityQueue.add(edge);
                    }
                }
                while (!priorityQueue.isEmpty()){
                    //弹出解锁边中最小的边
                    Edge edge = priorityQueue.poll();
                    //可能有个新点
                    Node toNode = edge.to;
                    if (!nodeSet.contains(toNode)){
                        nodeSet.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
            break; //防森林
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
        Set<Edge> edges = kruskalMST(graph);
        System.out.println(edges);
    }
}
