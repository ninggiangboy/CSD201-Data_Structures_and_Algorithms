package data_structures.graph;

public class WGraph extends Graph {

    final protected int INF;

    public WGraph(int INF) {
        super();
        this.INF = INF;
    }

    public WGraph() {
        this(99);
    }

    @Override
    public void addEdge(char src, char dest) {
        addEdge(src - 'A', dest - 'A', INF);
    }

    public void addEdge(char src, char dest, int weight) {
        addEdge(src - 'A', dest - 'A', weight);
    }

    @Override
    public void addEdge(int src, int dest) {
        addEdge(src, dest, INF);
    }

    public void addEdge(int src, int dest, int weight) {
        checkPositionVertex(src);
        checkPositionVertex(dest);
        if (a[src][dest] > 0)
            throw new IllegalStateException("Edge is already exist!");
        a[src][dest] = a[dest][src] = weight;
    }
}
