package data_structures.graph;

import data_structures.list.Queue;
import data_structures.list.Stack;

import java.util.Arrays;

public class Graph {

    protected int[][] a;
    protected int size = 0;
    final protected char[] label = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public Graph(int[][] a) {
        this(a.length);
        setData(a);
    }

    public Graph(int size) {
        a = new int[size][size];
    }

    public Graph() {
        this(20);
    }

    public void setData(int[][] b) {
        size = b.length;
        a = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(b[i], 0, a[i], 0, size);
        }
    }

    protected int deg(int i) {
        int deg = 0;
        for (int j = 0; j < size; j++)
            deg += a[i][j];
        deg += a[i][i];
        return deg;
    }

    public void addEdge(char src, char dest) {
        addEdge(src - 'A', dest - 'A');
    }

    public void addEdge(int src, int dest) {
        checkPositionVertex(src);
        checkPositionVertex(dest);
        if (a[src][dest] == 1)
            throw new IllegalStateException("Edge is already exist!");
        a[src][dest] = a[dest][src] = 1;
    }

    public void addVertex() {
        if (size < label.length) {
            size++;
            return;
        }
        int[][] newAdj = new int[size][];
        cloneArr(a, newAdj);
        throw new IllegalStateException("Graph is already at maximum capacity!");
    }

    protected void visit(int i) {
        System.out.print(label[i] + " ");
    }

    private void breadth(boolean[] visited, int k) {
        Queue<Integer> q = new Queue<Integer>();
        int current;
        q.enqueue(k);
        visited[k] = true;
        while (!q.isEmpty()) {
            current = q.dequeue();
            visit(current);
            for (int i = 0; i < size; i++) {
                if (!visited[i] && a[current][i] > 0) {
                    q.enqueue(i);
                    visited[i] = true;
                }
            }
        }
    }

    public void breadth(int src) {
        boolean[] visited = new boolean[20];
        for (int i = 0; i < size; i++)
            visited[i] = false;
        breadth(visited, src);
        for (int i = 0; i < size; i++)
            if (!visited[i])
                breadth(visited, i);
    }

    private void depth(boolean[] visited, int k) {
        visit(k);
        visited[k] = true;
        for (int i = 0; i < size; i++) {
            if (!visited[i] && a[k][i] > 0)
                depth(visited, i);
        }
    }

    public void depth(int src) {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < size; i++)
            visited[i] = false;
        depth(visited, src);
        for (i = 0; i < size; i++)
            if (!visited[i])
                depth(visited, i);
    }

    protected boolean hasIsolated() {
        for (int i = 0; i < size; i++) {
            if (deg(i) == 0) {
                return true;
            }
        }
        return false;
    }

    protected boolean isConnected() {
        boolean[] visited = new boolean[size];
        for (int i = 0; i < size; i++) {
            visited[i] = false;
        }
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        visited[0] = true;
        while (!stack.isEmpty()) {
            int r = (int) stack.pop();
            for (int i = 0; i < size; i++) {
                if (!visited[i] && a[r][i] > 0) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    protected boolean isUnDirected() {
        int i, j;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                if (a[i][j] != a[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean allDegEven() {
        for (int i = 0; i < size; i++) {
            if (deg(i) % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    protected int numOddDegree() {
        int oddDeg = 0;
        for (int i = 0; i < size; i++) {
            if (deg(i) % 2 == 1) {
                oddDeg++;
            }
        }
        return oddDeg;
    }

    protected boolean isPositionVertex(int i) {
        return i > 0 && i < size;
    }

    protected void checkPositionVertex(int index) {
        if (!isPositionVertex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    protected int[][] cloneArr(int[][] array) {
        int[][] clone = Arrays.copyOf(array, array.length);
        for (int i = 0; i < a.length; i++) {
            clone[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return clone;
    }

    protected void cloneArr(int[][] oldArr, int[][] newArr) {
        if (newArr.length < oldArr.length || newArr[0].length < oldArr[0].length)
            return;
        for (int i = 0; i < oldArr.length; i++) {
            System.arraycopy(oldArr[i], 0, newArr[i], 0, oldArr[0].length);
        }
    }
}
