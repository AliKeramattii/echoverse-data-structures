package Phase3;

public class Phase3Main {
    public static void main(String[] args) {

        GeneralTree tree = new GeneralTree();

        tree.insert(null, "Producer");
        tree.insert("Producer", "Editor");
        tree.insert("Producer", "Host");
        tree.insert("Producer", "SoundEngineer");
        tree.insert("Editor", "ScriptWriter");
        tree.insert("SoundEngineer", "Intern1");
        tree.insert("SoundEngineer", "Intern2");
        tree.insert("Manager", "Assistant"); // Invalid parent

        tree.display();

        tree.searchBFS("Intern2");
        tree.searchDFS("ScriptWriter");
        tree.searchBFS("Manager");

        tree.delete("SoundEngineer");
        tree.display();
    }
}
