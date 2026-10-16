package Phase1;

public class Phase1Main {
    public static void main(String[] args) {

        AVLTree tree = new AVLTree();

        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(25);
        tree.insert(35);
        tree.insert(50);

        tree.display();
        System.out.println();

        System.out.println(tree.search(25)
                ? "Channel 25 is authorized"
                : "Channel 25 is NOT authorized");

        System.out.println(tree.search(60)
                ? "Channel 60 is authorized"
                : "Channel 60 is NOT authorized");

        System.out.println();

        tree.delete(40);
        tree.display();
        System.out.println();

        tree.delete(20);
        tree.display();
    }
}
