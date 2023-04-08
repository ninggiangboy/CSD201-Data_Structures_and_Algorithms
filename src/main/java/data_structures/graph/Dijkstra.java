package data_structures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstra extends WGraph {

    private int[] dist;
    private boolean[] visited;
    private int[] prev;
    private List<Integer> verticesSelected;
    private List<Integer> pathDijkstra;

    public Dijkstra() {
        super();
    }

    private void dijkstra(int source) {
        dist = new int[size];
        visited = new boolean[size];
        prev = new int[size];
        verticesSelected = new ArrayList<>();

        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);
        Arrays.fill(prev, -1);
        dist[source] = 0;

        for (int i = 0; i < size; i++) {
            int u = findMinDistance();
            if (u == -1)
                break;
            visited[u] = true;
            verticesSelected.add(u);
            for (int j = 0; j < size; j++) {
                if (!visited[j] && a[u][j] != 0
                        && dist[u] != INF
                        && dist[u] + a[u][j] < dist[j]) {
                    dist[j] = dist[u] + a[u][j];
                    prev[j] = u;
                }
            }
        }
    }

    private int findMinDistance() {
        int min = INF;
        int minIndex = -1;
        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void dijkstraPath(int source, int target) {
        checkPositionVertex(source);
        checkPositionVertex(target);
        dijkstra(source);
        pathDijkstra = new ArrayList<>();
        int u = target;
        while (u != source) {
            pathDijkstra.add(0, u);
            u = prev[u];
        }
        pathDijkstra.add(0, source);
    }

    public List<Integer> getDijkstraPath(int source, int target) {
        dijkstraPath(source, target);
        return pathDijkstra;
    }

    public int shortestDistance(int target) {
        return dist[target];
    }
}
