package Phase4;

import java.util.*;

/**
 * MinHeap implements a priority queue using a binary min-heap.
 *
 * Properties:
 * - Complete binary tree
 * - Parent node always has a smaller priority than its children
 * - Root element is always the minimum-priority element
 *
 * Internal representation:
 * - Uses an ArrayList to store heap nodes
 * - Index relationships:
 *      Parent(i) = (i - 1) / 2
 *      Left(i)   = 2 * i + 1
 *      Right(i)  = 2 * i + 2
 */
public class MinHeap {

    // Array-based storage for the heap
    private List<HeapNode> heap = new ArrayList<>();

    /**
     * Inserts a new element into the heap.
     *
     * Steps:
     * 1. Add the element at the end of the list
     * 2. Restore heap property using heapifyUp
     *
     * Time Complexity:
     *  - Best Case: O(1)
     *  - Average Case: O(log n)
     *  - Worst Case: O(log n)
     *
     * Space Complexity:
     *  - O(1) auxiliary (in-place)
     */
    public void insert(String id, int priority) {
        heap.add(new HeapNode(id, priority));
        heapifyUp(heap.size() - 1);
    }

    /**
     * Restores heap property by moving a node upward.
     * Called after insertion.
     *
     * While the node's priority is smaller than its parent's,
     * swap them.
     *
     * Time Complexity: O(log n)
     */
    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;

            if (heap.get(i).priority < heap.get(parent).priority) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    /**
     * Removes and prints the minimum element (root of the heap).
     *
     * Steps:
     * 1. Save the root
     * 2. Replace root with last element
     * 3. Remove last element
     * 4. Restore heap property using heapifyDown
     *
     * Time Complexity:
     *  - Best Case: O(1)
     *  - Average Case: O(log n)
     *  - Worst Case: O(log n)
     *
     * Space Complexity: O(1)
     */
    public void extractMin() {
        if (heap.isEmpty()) return;

        HeapNode min = heap.get(0);
        System.out.println("Extracted episode: " + min.id +
                " with priority " + min.priority + "\n");

        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(0);
    }

    /**
     * Restores heap property by pushing a node downward.
     * Called after extraction or deletion.
     *
     * Compares the node with its left and right children
     * and swaps with the smallest.
     *
     * Time Complexity: O(log n)
     */
    private void heapifyDown(int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heap.size() &&
                heap.get(left).priority < heap.get(smallest).priority) {
            smallest = left;
        }

        if (right < heap.size() &&
                heap.get(right).priority < heap.get(smallest).priority) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    /**
     * Deletes a heap element by its ID.
     *
     * Steps:
     * 1. Linearly search for the ID
     * 2. Replace it with the last element
     * 3. Remove last element
     * 4. Restore heap property
     *
     * Time Complexity:
     *  - Best Case: O(1)
     *  - Worst Case: O(n)
     *
     * Space Complexity: O(1)
     */
    public void delete(String id) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).id.equals(id)) {
                heap.set(i, heap.get(heap.size() - 1));
                heap.remove(heap.size() - 1);
                heapifyDown(i);
                System.out.println("Episode " + id + " deleted\n");
                return;
            }
        }
    }

    /**
     * Displays heap contents in array order.
     *
     * Note:
     * This is NOT sorted order.
     * It reflects the internal heap structure.
     *
     * Time Complexity: O(n)
     */
    public void display() {
        for (HeapNode node : heap) {
            System.out.print(node.id + "(" + node.priority + ") ");
        }
        System.out.println("\n");
    }

    /**
     * Displays heap elements sorted by priority.
     *
     * This does NOT modify the heap.
     * A temporary list is created and sorted.
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    public void heapSort() {
        List<HeapNode> temp = new ArrayList<>(heap);
        temp.sort(Comparator.comparingInt(a -> a.priority));

        System.out.println("Sorted episodes by priority:");
        for (HeapNode n : temp) {
            System.out.print(n.id + "(" + n.priority + ") ");
        }
        System.out.println();
    }

    /**
     * Swaps two elements in the heap list.
     *
     * Time Complexity: O(1)
     */
    private void swap(int i, int j) {
        HeapNode temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
