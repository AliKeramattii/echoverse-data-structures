package Phase3;

import java.util.*;

/**
 * GeneralTree implements a general tree structure.
 * It provides core operations like insertion, BFS/DFS search, and subtree deletion.
 */
public class GeneralTree {

    private TreeNode root; // The root node of the tree.

    /**
     * Inserts a new child node under a specified parent node.
     * Time Complexity: O(N) where N is the number of nodes, due to the O(N) call to findNodeBFS.
     * It does not check for duplicate child names.
     * @param parentName The name of the parent node. Null if inserting the root.
     * @param childName The name of the new child node.
     */
    public void insert(String parentName, String childName) {

        // Case 1: If tree is empty, initialize the root node.
        if (root == null) {
            if (parentName == null) {
                root = new TreeNode(childName);
            }
            // If tree is empty but parentName is not null, insertion is implicitly ignored.
            return;
        }

        // Case 2: Find the parent node using Breadth-First Search (O(N)).
        TreeNode parent = findNodeBFS(parentName);

        // Error handling for non-existent parent.
        if (parent == null) {
            System.out.println("Parent " + parentName + " does not exist");
            return;
        }

        // Add the new child node to the parent's children list.
        parent.children.add(new TreeNode(childName));
    }

    /**
     * Searches for a target node using Breadth-First Search (BFS).
     * Time Complexity: O(N) in the worst case, where N is the number of nodes.
     * @param target The name of the node to find.
     */
    public void searchBFS(String target) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        // parentMap is used to store parent pointers for path reconstruction.
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        queue.add(root);
        parentMap.put(root, null); // Root has no parent.

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            // Check if target is found.
            if (current.name.equals(target)) {
                // Path reconstruction is O(H) where H is depth/height of the node.
                printPath(current, parentMap, "BFS");
                return;
            }

            // Enqueue children and record parent for the path.
            for (TreeNode child : current.children) {
                parentMap.put(child, current);
                queue.add(child);
            }
        }

        System.out.println("Node " + target + " NOT found");
    }

    /**
     * Searches for a target node using Depth-First Search (DFS) (pre-order approach).
     * Time Complexity: O(N) in the worst case, where N is the number of nodes.
     * @param target The name of the node to find.
     */
    public void searchDFS(String target) {
        // parentMap is used to store parent pointers for path reconstruction.
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        // Start recursive DFS from the root.
        if (!dfsHelper(root, target, parentMap)) {
            System.out.println("Node " + target + " NOT found");
        }
    }

    /**
     * Recursive helper for DFS traversal (Pre-order).
     * @param node The current node being visited.
     * @param target The name of the node to find.
     * @param parentMap Map to track parent pointers for path reconstruction.
     * @return true if the target is found in the current subtree, false otherwise.
     */
    private boolean dfsHelper(TreeNode node, String target, Map<TreeNode, TreeNode> parentMap) {
        if (node == null) return false;

        // Base case: target found.
        if (node.name.equals(target)) {
            printPath(node, parentMap, "DFS");
            return true;
        }

        // Recurse on children.
        for (TreeNode child : node.children) {
            parentMap.put(child, node); // Record parent before recursion.
            if (dfsHelper(child, target, parentMap))
                return true; // Stop and return immediately if found.
        }
        return false;
    }

    /**
     * Deletes a node and its entire subtree, by removing it from its parent's children list.
     * Time Complexity: O(N) in the worst case, as it uses a BFS-like approach to find the parent.
     * Note: This implementation does not explicitly clear the memory of the deleted subtree nodes,
     * relying on garbage collection.
     * @param target The name of the node to delete.
     */
    public void delete(String target) {
        if (root == null) return;

        // Special case: deleting the root.
        if (root.name.equals(target)) {
            root = null;
            return;
        }

        // Use BFS to find the parent of the target node.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            // Use Iterator to safely remove an element while traversing the list.
            Iterator<TreeNode> it = current.children.iterator();
            while (it.hasNext()) {
                TreeNode child = it.next();

                // Check if the child is the target node.
                if (child.name.equals(target)) {
                    it.remove(); // Remove the target node (and its subtree) from the parent's list.
                    System.out.println("Node " + target + " and its subtree deleted\n");
                    return;
                }

                // Continue BFS traversal.
                queue.add(child);
            }
        }
    }

    /**
     * Displays the tree structure using a simple pre-order traversal format.
     * Time Complexity: O(N).
     */
    public void display() {
        if (root == null) return;
        displayNode(root);
        System.out.println();
    }

    /**
     * Recursive helper to display a node and its direct children.
     * @param node The current node to display.
     */
    private void displayNode(TreeNode node) {
        // Print the current node's name.
        System.out.print(node.name + ": ");

        // Print all direct children, comma-separated.
        for (int i = 0; i < node.children.size(); i++) {
            System.out.print(node.children.get(i).name);
            if (i < node.children.size() - 1)
                System.out.print(",");
        }
        System.out.println();

        // Recursively call display for all children (Pre-order traversal).
        for (TreeNode child : node.children)
            displayNode(child);
    }

    /**
     * Helper method to find a node by name using Breadth-First Search (BFS).
     * Time Complexity: O(N) where N is the number of nodes.
     * @param name The name of the node to find.
     * @return The found TreeNode, or null if not found.
     */
    private TreeNode findNodeBFS(String name) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode current = q.poll();
            if (current.name.equals(name)) return current; // Node found.
            q.addAll(current.children); // Add all children to the queue for next level processing.
        }
        return null; // Node not found.
    }

    /**
     * Helper method to reconstruct and print the path from a found node back to the root.
     * Time Complexity: O(H) where H is the height (depth) of the found node.
     * @param node The target node found.
     * @param parentMap The map containing parent pointers.
     * @param type The search type (BFS or DFS) for display.
     */
    private void printPath(TreeNode node, Map<TreeNode, TreeNode> parentMap, String type) {
        List<String> path = new ArrayList<>();

        // Traverse backwards from the node to the root using the parent map.
        while (node != null) {
            path.add(node.name);
            node = parentMap.get(node);
        }

        Collections.reverse(path); // Reverse the path to get root -> target order.

        // Print the path structure.
        System.out.println("Path (" + type + "): " + String.join(" -> ", path));
    }
}
