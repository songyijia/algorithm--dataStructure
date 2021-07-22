package algorithm.graph;

import java.util.ArrayList;

public class Node {
    int value;
    int in;
    int out;
    ArrayList<Node> nexts;
    ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
