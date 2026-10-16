package Phase2;

import java.util.*;

/**
 * Implements Dijkstra's Single-Source Shortest Path algorithm.
 * Works with non-negative weighted undirected graphs.
 *
 * Phases:
 * 1. Initialize distance & parent arrays
 * 2. Select unvisited vertex with minimum distance
 * 3. Relax edges
 * 4. Repeat until all vertices visited
 *
 * Complexity:
 * Time: O(V^2)
 * Space: O(V)
 */
public class Dijkstra {

    /** Computes and displays shortest paths from the given source vertex. */
    public static void shortestPath(Graph g, String source) {
        int n = g.size();

// Defensive check
        if (n == 0) {
            System.out.println("Graph is empty. Cannot run Dijkstra.");
            return;
        }

        if (!g.containsVertex(source)) {
            System.out.println("Error: Source vertex '" + source + "' not found in graph.");
            return;
        }

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        int srcIndex = g.getIndex(source);
        dist[srcIndex] = 0;

// O(V^2) core loop
        for (int i = 0; i < n - 1; i++) {
            int u = selectMinVertex(dist, visited, n);
            if (u == -1) break; // all remaining vertices are unreachable
            visited[u] = true;

// Relax edges from u
            for (int v = 0; v < n; v++) {
                int weight = g.getMatrix()[u][v];
                if (weight > 0 && !visited[v] && dist[u] != Integer.MAX_VALUE
                        && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                }
            }
        }

        printResults(g, source, dist, parent);
    }

    /** Helper: selects the unvisited vertex with the smallest distance. */
    private static int selectMinVertex(int[] dist, boolean[] visited, int n) {
        int min = Integer.MAX_VALUE, idx = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                idx = i;
            }
        }
        return idx;
    }

    /** Prints the computed paths and costs neatly. */
    private static void printResults(Graph g, String source, int[] dist, int[] parent) {
        System.out.println("===== Dijkstra Results (Source: " + source + ") =====\n");
        for (int i = 0; i < g.size(); i++) {
            String vertex = g.getVertex(i);
            System.out.print("Target: " + vertex + " | ");

            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("No reachable path from " + source);
                continue;
            }

            System.out.print("Cost = " + dist[i] + " | Path: ");
            printPath(parent, i, g);
            System.out.println();
        }
        System.out.println("==============================================\n");
    }

    /** Recursively prints path from source to the given vertex. */
    private static void printPath(int[] parent, int j, Graph g) {
        if (parent[j] == -1) {
            System.out.print(g.getVertex(j));
            return;
        }
        printPath(parent, parent[j], g);
        System.out.print(" -> " + g.getVertex(j));
    }
}
