import java.util.*;
import java.io.*;

public class HuffmanCoding {
    private static double acwl = 0.0;
    private static ArrayList<Node> hnodes = new ArrayList<Node>();

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Node> tree = new ArrayList<Node>();
        String alphabet;
        double probability;

        File inputFile = new File("dict_probability-of-occurrence.txt");
        Scanner File = new Scanner(inputFile);

        while (File.hasNextLine()) {
            String Line = File.nextLine();
            Scanner scanner = new Scanner(Line);
            alphabet = scanner.next();
            probability = Double.parseDouble(scanner.next());
            Node node = new Node(alphabet, probability, null, null);
            tree.add(node);
            scanner.close();
        }
        File.close();
        int count = 26;
        while (tree.size() != 1) {
            Collections.sort(tree, new Node.compProb());
            if (count == 26) {
                count = print(tree, count);
            }
            Node small1 = tree.get(0);
            Node small2 = tree.get(1);
            tree.remove(small1);
            tree.remove(small2);
            Node node = new Node("NODE", small1.probability + small2.probability, small1, small2);
            tree.add(node);
            count = print(tree, count);
        }

        coding(tree.get(0));
        displayTree(tree.get(0));
        System.out.println("符号(結果)：");
        for (Node hn : hnodes) {
            System.out.println(hn.symbol + ": " + hn.code);
        }
        System.out.println("平均符号語長: " + acwl);
    }

    private static int print(ArrayList<Node> tree, int count) {
        System.out.println("【" + count + "層目】");
        for (Node n : tree) {
            System.out.println(n.symbol + ": " + n.probability);    //末端以外も表示
        }
        System.out.println();
        return --count;
    }

    private static void coding(Node root) {
        if (root.left != null && root.right != null) {
            root.left.code += (root.code + "0");
            root.right.code += (root.code + "1");
            coding(root.left);
            coding(root.right);
        }
    }

    private static void displayTree(Node root) {
        if (root != null) {
            displayTree(root.left);
            if (root.left == null && root.right == null) {
                acwl += (root.probability * root.code.length());
                hnodes.add(root);
            }
            displayTree(root.right);
        }
        Collections.sort(hnodes, new Node.compSym());
    }
}
