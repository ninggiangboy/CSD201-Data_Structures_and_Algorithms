package data_structures.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Prim extends WGraph {

    private int[] spanningWeight;
    private int[] prev;

    public Prim() {
        super();
    }

    public void prim() {
        boolean[] visited = new boolean[size];
        spanningWeight = new int[size];
        prev = new int[size];

        Arrays.fill(visited, false);
        Arrays.fill(spanningWeight, INF);
        Arrays.fill(prev, -1);

        PriorityQueue<Integer> spanningEdges = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i, Integer j) {
                return Integer.compare(spanningWeight[i], spanningWeight[j]);
            }
        });

        spanningWeight[0] = 0;
        spanningEdges.offer(0);

        while (!spanningEdges.isEmpty()) {
            int u = spanningEdges.poll();
            visited[u] = true;

            for (int v = 0; v < size; v++) {
                if (a[u][v] != 0 && !visited[v] && a[u][v] < spanningWeight[v]) {
                    spanningWeight[v] = a[u][v];
                    prev[v] = u;
                    spanningEdges.offer(v);
                }
            }
        }
    }
}
