package algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图，由点集和边集组成。
 * 表达：1）邻接表法 2）邻接矩阵法 3）除此之外还有其它众多方式
 */
public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
