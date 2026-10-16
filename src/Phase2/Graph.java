package Phase2;

import java.util.*;

 /**
 * Graph class using adjacency matrix representation.
 * Supports labeled vertices and weighted undirected edges.
 *
 * Key Features:
 * - Dynamic vertex labeling (String to int mapping)
 * - Automatic vertex index assignment
 * - Undirected edges (symmetric matrix)
 *
 * Constraints:
 * - Supports up to 20 vertices
 * - Non-negative edge weights only
 *
 * Time Complexity:
 * Add Edge: O(1)
 * Space Complexity: O(V^2)
 */
public class Graph {
    private static final int MAX_VERTICES = 20;
    private final Map<String, Integer> indexMap = new HashMap<>();
    private final List<String> vertices = new ArrayList<>();
    private final int[][] adjMatrix = new int[MAX_VERTICES][MAX_VERTICES];
    private int size = 0;

    /** Adds a weighted edge between two vertices */
    public void addEdge(String from, String to, int weight) {
        if (weight < 0) {
            System.out.println("Error: Negative weights not allowed in Dijkstra's algorithm.");
            return;
        }

        if (size >= MAX_VERTICES) {
            System.out.println("Error: Maximum vertex limit (" + MAX_VERTICES + ") reached.");
            return;
        }

        if (!indexMap.containsKey(from)) {
            indexMap.put(from, size++);
            vertices.add(from);
        }

        if (!indexMap.containsKey(to)) {
            indexMap.put(to, size++);
            vertices.add(to);
        }

        int i = indexMap.get(from);
        int j = indexMap.get(to);
        adjMatrix[i][j] = weight;
        adjMatrix[j][i] = weight; // undirected
    }

    public boolean containsVertex(String v) {
        return indexMap.containsKey(v);
    }

    public int[][] getMatrix() {
        return adjMatrix;
    }

    public int size() {
        return size;
    }

    public String getVertex(int i) {
        return vertices.get(i);
    }

    public int getIndex(String v) {
// Defensive: avoid NPE for missing vertex
        return indexMap.getOrDefault(v, -1);
    }

    /** Prints the adjacency matrix in labeled form */
    public void printGraph() {
        System.out.println("===== Graph Adjacency Matrix =====");
        System.out.print("     ");
        for (String v : vertices) {
            System.out.print(String.format("%5s", v));
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(String.format("%5s", vertices.get(i)));
            for (int j = 0; j < size; j++) {
                System.out.print(String.format("%5d", adjMatrix[i][j]));
            }
            System.out.println();
        }
        System.out.println("==================================\n");
    }
}
