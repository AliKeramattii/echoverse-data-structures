package Phase4;

/**
 * HeapNode represents a single element stored inside the MinHeap.
 * Each node contains:
 *  - an id (String): uniquely identifies the episode/item
 *  - a priority (int): determines ordering in the MinHeap
 *
 * Lower priority values indicate higher importance
 * (i.e., priority 1 is extracted before priority 5).
 */
public class HeapNode {

    // Unique identifier for the heap element (e.g., episode ID)
    String id;

    // Priority value used for heap ordering (smaller = higher priority)
    int priority;

    /**
     * Constructor to initialize a heap node.
     *
     * @param id        unique identifier of the item
     * @param priority integer priority value
     */
    public HeapNode(String id, int priority) {
        this.id = id;
        this.priority = priority;
    }
}
