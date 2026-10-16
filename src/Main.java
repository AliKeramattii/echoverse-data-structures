import Phase1.AVLTree;
import Phase2.Dijkstra;
import Phase2.Graph;
import Phase3.GeneralTree;
import Phase4.MinHeap;

import java.util.Scanner;


/**
 * EchoVerse – Interactive Driver Program
 * Grounded in LEC05–LEC11 (Stacks, Trees, Graphs, Heaps)
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // Phase instances (persist across menu calls)
    private static AVLTree avlTree = new AVLTree();          // Phase 1
    private static Graph graph = new Graph();                // Phase 2
    private static GeneralTree generalTree = new GeneralTree(); // Phase 3
    private static MinHeap minHeap = new MinHeap();          // Phase 4

    public static void main(String[] args) {

        int choice;
        do {
            printMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> phase1Menu();
                case 2 -> phase2Menu();
                case 3 -> phase3Menu();
                case 4 -> phase4Menu();
                case 0 -> System.out.println("Exiting EchoVerse. Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    // ================= MAIN MENU =================

    private static void printMainMenu() {
        System.out.println("\n===== EchoVerse Main Menu =====");
        System.out.println("1. Phase 1 – Channel Indexing (AVL Tree)");
        System.out.println("2. Phase 2 – Broadcast Routing (Graph + Dijkstra)");
        System.out.println("3. Phase 3 – Broadcast Hierarchy (General Tree)");
        System.out.println("4. Phase 4 – Priority Broadcasting (Min-Heap)");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    // ================= PHASE 1 =================
    // AVL TREE (LEC06, LEC09)

    private static void phase1Menu() {
        int choice;
        do {
            System.out.println("\n--- Phase 1: AVL Tree ---");
            System.out.println("1. Insert Channel ID");
            System.out.println("2. Delete Channel ID");
            System.out.println("3. Search Channel ID");
            System.out.println("4. Display AVL Tree");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Channel ID: ");
                    avlTree.insert(scanner.nextInt());
                }
                case 2 -> {
                    System.out.print("Enter Channel ID: ");
                    avlTree.delete(scanner.nextInt());
                }
                case 3 -> {
                    System.out.print("Enter Channel ID: ");
                    int id = scanner.nextInt();
                    System.out.println(
                            avlTree.search(id)
                                    ? "Channel is authorized"
                                    : "Channel is NOT authorized"
                    );
                }
                case 4 -> avlTree.display();
            }
        } while (choice != 0);
    }

    // ================= PHASE 2 =================
    // GRAPH + DIJKSTRA (LEC11)

    private static void phase2Menu() {
        int choice;
        do {
            System.out.println("\n--- Phase 2: Graph & Dijkstra ---");
            System.out.println("1. Add Edge");
            System.out.println("2. Run Dijkstra");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("From vertex: ");
                    String from = scanner.nextLine();
                    System.out.print("To vertex: ");
                    String to = scanner.nextLine();
                    System.out.print("Weight: ");
                    int w = scanner.nextInt();
                    graph.addEdge(from, to, w);
                }
                case 2 -> {
                    System.out.print("Enter source vertex: ");
                    String src = scanner.nextLine();
                    Dijkstra.shortestPath(graph, src);
                }
            }
        } while (choice != 0);
    }

    // ================= PHASE 3 =================
    // GENERAL TREE + BFS/DFS (LEC06, LEC05)

    private static void phase3Menu() {
        int choice;
        do {
            System.out.println("\n--- Phase 3: General Tree ---");
            System.out.println("1. Insert Node");
            System.out.println("2. Search (BFS)");
            System.out.println("3. Search (DFS)");
            System.out.println("4. Delete Node");
            System.out.println("5. Display Tree");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Parent name (or 'null'): ");
                    String parent = scanner.nextLine();
                    System.out.print("Child name: ");
                    String child = scanner.nextLine();
                    generalTree.insert(parent.equals("null") ? null : parent, child);
                }
                case 2 -> {
                    System.out.print("Target: ");
                    generalTree.searchBFS(scanner.nextLine());
                }
                case 3 -> {
                    System.out.print("Target: ");
                    generalTree.searchDFS(scanner.nextLine());
                }
                case 4 -> {
                    System.out.print("Node to delete: ");
                    generalTree.delete(scanner.nextLine());
                }
                case 5 -> generalTree.display();
            }
        } while (choice != 0);
    }

    // ================= PHASE 4 =================
    // MIN-HEAP (LEC07, LEC10)

    private static void phase4Menu() {
        int choice;
        do {
            System.out.println("\n--- Phase 4: Min Heap ---");
            System.out.println("1. Insert Episode");
            System.out.println("2. Extract Min");
            System.out.println("3. Delete Episode");
            System.out.println("4. Display Heap");
            System.out.println("5. Heap Sort");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Episode ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Priority: ");
                    int p = scanner.nextInt();
                    minHeap.insert(id, p);
                }
                case 2 -> minHeap.extractMin();
                case 3 -> {
                    System.out.print("Episode ID: ");
                    minHeap.delete(scanner.nextLine());
                }
                case 4 -> minHeap.display();
                case 5 -> minHeap.heapSort();
            }
        } while (choice != 0);
    }
}
