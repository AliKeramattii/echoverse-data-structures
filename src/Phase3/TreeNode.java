package Phase3;

import java.util.*;

/**
 * TreeNode represents a single node in the GeneralTree.
 * It holds the node's identifier (name) and a list of its children.
 * Note: Fields are declared with default visibility (package-private) and are not encapsulated.
 */
public class TreeNode {
    // Identifier for the node.
    String name;
    // List of child nodes. Using LinkedList for flexibility in adding/removing children.
    List<TreeNode> children;

    /**
     * Constructor for TreeNode.
     * @param name The unique identifier for the node.
     */
    public TreeNode(String name) {
        this.name = name;
        this.children = new LinkedList<>(); // Initializes the children list.
    }
}
