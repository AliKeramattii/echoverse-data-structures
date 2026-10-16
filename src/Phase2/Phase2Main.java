package Phase2;

public class Phase2Main {
    public static void main(String[] args) {

        Graph g = new Graph();

        g.addEdge("A", "B", 4);
        g.addEdge("A", "C", 2);
        g.addEdge("B", "C", 1);
        g.addEdge("B", "D", 5);
        g.addEdge("C", "D", 8);
        g.addEdge("C", "E", 10);
        g.addEdge("D", "E", 2);

        Dijkstra.shortestPath(g, "A");
        Dijkstra.shortestPath(g, "C");
    }
}
