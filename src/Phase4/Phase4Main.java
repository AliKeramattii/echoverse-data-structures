package Phase4;

public class Phase4Main {
    public static void main(String[] args) {

        MinHeap heap = new MinHeap();

        heap.insert("E1", 5);
        heap.insert("E2", 2);
        heap.insert("E3", 8);
        heap.insert("E4", 1);
        heap.insert("E5", 3);

        heap.display();

        heap.extractMin();
        heap.display();

        heap.delete("E3");
        heap.display();

        heap.heapSort();
    }
}
