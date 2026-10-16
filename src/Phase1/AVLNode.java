package Phase1;

/**
 * AVLNode represents a single node in an AVL Tree.
 * Each node stores:
 * - channelId (key)
 * - height (for balancing)
 * - left and right child references
 */
public class AVLNode {
    int channelId;
    int height;
    AVLNode left, right;

    public AVLNode(int channelId) {
        this.channelId = channelId;
        this.height = 1; // New node is initially a leaf
    }
}
