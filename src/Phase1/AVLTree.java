package Phase1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AVLTree implements a self-balancing Binary Search Tree.
 *
 * Supported Operations:
 * - Insert
 * - Delete
 * - Search
 * - Display
 *
 * Time Complexity:
 * Insert: O(log n)
 * Delete: O(log n)
 * Search: O(log n)
 *
 * Space Complexity:
 * O(n)
 */
public class AVLTree {

    private AVLNode root;

    /* ===================== Utility Methods ===================== */

    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    /* ===================== Rotations ===================== */

    // Right rotation (LL case)
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotation (RR case)
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    /* ===================== Insert ===================== */

    public void insert(int id) {
        root = insertNode(root, id);
    }

    private AVLNode insertNode(AVLNode node, int id) {
        if (node == null)
            return new AVLNode(id);

        if (id < node.channelId)
            node.left = insertNode(node.left, id);
        else if (id > node.channelId)
            node.right = insertNode(node.right, id);
        else {
            System.out.println("Duplicate Channel ID ignored: " + id);
            return node;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        // LL
        if (balance > 1 && id < node.left.channelId)
            return rightRotate(node);

        // RR
        if (balance < -1 && id > node.right.channelId)
            return leftRotate(node);

        // LR
        if (balance > 1 && id > node.left.channelId) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && id < node.right.channelId) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /* ===================== Search ===================== */

    public boolean search(int id) {
        return searchNode(root, id);
    }

    private boolean searchNode(AVLNode node, int id) {
        if (node == null) return false;
        if (id == node.channelId) return true;
        return id < node.channelId
                ? searchNode(node.left, id)
                : searchNode(node.right, id);
    }

    /* ===================== Delete ===================== */

    public void delete(int id) {
        root = deleteNode(root, id);
    }

    private AVLNode deleteNode(AVLNode node, int key) {
        if (node == null) return null;

        if (key < node.channelId)
            node.left = deleteNode(node.left, key);
        else if (key > node.channelId)
            node.right = deleteNode(node.right, key);
        else {
            // Node with one or no child
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                // Node with two children
                AVLNode temp = minValueNode(node.right);
                node.channelId = temp.channelId;
                node.right = deleteNode(node.right, temp.channelId);
            }
        }

        if (node == null) return null;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        // Rebalancing
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode minValueNode(AVLNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    /* ===================== Display ===================== */

    /**
     * Displays the AVL Tree.
     * This representation is ideal for grading and visualization.
     */
    public void display() {
        displayNode(root);
    }

    private void displayNode(AVLNode node) {
        if (node == null) return;

        // Only display nodes that have at least one child
        if (node.left != null || node.right != null) {
            String left = node.left == null ? "-1" : String.valueOf(node.left.channelId);
            String right = node.right == null ? "-1" : String.valueOf(node.right.channelId);
            System.out.println(node.channelId + " " + left + "," + right);
        }

        // Continue traversal
        displayNode(node.left);
        displayNode(node.right);
    }
}
