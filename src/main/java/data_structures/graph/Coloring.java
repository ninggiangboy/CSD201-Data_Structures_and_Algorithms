package data_structures.graph;

public class Coloring extends Graph {

    private char[] vertexColors;
    private int[] vertexOrder;
    private final char[] colors = "123456789".toCharArray();
    private int[] deg;

    public Coloring() {
        super();
    }

    public void sequentialColoring() {
        vertexColors = new char[size];
        vertexOrder = new int[size];

        for (int i = 0; i < size; i++) {
            vertexOrder[i] = i;
        }

        sortByDeg();

        for (int i = 0; i < size; i++) {
            int currentVertex = vertexOrder[i];
            boolean[] neighborColors = new boolean[colors.length];

            for (int j = 0; j < size; j++) {
                if (a[currentVertex][j] == 1) {
                    char neighborColor = vertexColors[j];
                    if (neighborColor != 0) {
                        int neighborColorIndex = findIndexColor(colors, neighborColor);
                        neighborColors[neighborColorIndex] = true;
                    }
                }
            }

            int smallestColorIndex = 0;
            while (neighborColors[smallestColorIndex]) {
                smallestColorIndex++;
            }

            vertexColors[currentVertex] = colors[smallestColorIndex];
        }
    }

    private int findIndexColor(char[] array, char color) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == color) {
                return i;
            }
        }
        return -1;
    }

    private void sortByDeg() {
        for (int i = 0; i < size; i++) {
            deg[i] = deg(i);
        }
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (deg[vertexOrder[i]] < deg[vertexOrder[j]]) {
                    int temp = vertexOrder[i];
                    vertexOrder[i] = vertexOrder[j];
                    vertexOrder[j] = temp;
                }
            }
        }
    }
}
