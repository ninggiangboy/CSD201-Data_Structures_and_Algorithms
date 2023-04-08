package data_structures.graph;

import data_structures.list.Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Euler extends Graph {

    List<Integer> cycleEuler;
    List<Integer> pathEuler;

    public Euler() {
        super();
    }

    private List<Integer> euler(int start) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(start);
        List<Integer> euler = new ArrayList<>();

        int[][] array = cloneArr(a);
        int i;
        while (!stack.isEmpty()) {
            int current = (int) stack.top();
            for (i = 0; i < size; i++) {
                if (array[current][i] > 0)
                    break;
            }
            if (i == size) {
                stack.pop();
                euler.add(current);
            } else {
                stack.push(i);
                array[current][i]--;
                array[i][current]--;
            }
        }
        return euler;
    }

    private void eulerCycle(int start) {
        if (!hasEulerCycle()) {
            cycleEuler = null;
            return;
        }
        euler(start);
        cycleEuler = euler(start);
    }

    private void eulerPath(int start) {
        if (!hasEulerPath() || (numOddDegree() == 2 && deg(start) % 2 == 0)) {
            pathEuler = null;
            return;
        }
        euler(start);
        pathEuler = euler(start);
        Collections.reverse(pathEuler);
    }

    public List<Integer> getEulerCycle(int start) {
        checkPositionVertex(start);
        eulerCycle(start);
        return cycleEuler;
    }

    public List<Integer> getEulerPath(int start) {
        checkPositionVertex(start);
        eulerPath(start);
        return pathEuler;
    }

    private boolean hasEulerCycle() {
        return !hasIsolated() && isUnDirected() && isConnected() && allDegEven();
    }

    private boolean hasEulerPath() {
        int oddDegreeCount = numOddDegree();
        return (oddDegreeCount == 2 || oddDegreeCount == 0) && isConnected();
    }

    void printEulerPath() {
        for (int i = 0; i < pathEuler.size(); i++) {
            visit(pathEuler.get(i));
        }
        System.out.println();
    }

    void printEulerCycle() {
        for (int i = 0; i < cycleEuler.size(); i++) {
            visit(cycleEuler.get(i));
        }
        System.out.println();
    }
}
