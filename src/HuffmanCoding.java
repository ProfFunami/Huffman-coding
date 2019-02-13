import java.util.*;
import java.io.*;

public class HuffmanCoding {
    private static double acwl = 0.0;
    private static ArrayList<Node> hnodes = new ArrayList<Node>();

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Node> tree = new ArrayList<Node>();

        File inputFile = new File("dict_probability-of-occurrence.txt");
        Scanner inFile = new Scanner(inputFile);
        inFile.useDelimiter("[^A-za-z0-9 ']+");

        while (inFile.hasNextLine()) {
            String nextLine = inFile.nextLine();
            Scanner SandPScanner = new Scanner(nextLine);
            SandPScanner.useDelimiter(" ");
            String c = SandPScanner.next();
            String pString = SandPScanner.next();
            double p = Double.parseDouble(pString);
            Node hn = new Node(c, p, null, null);
            tree.add(hn);
            SandPScanner.close();
        }
        inFile.close();

        while (tree.size() > 1) {
            Collections.sort(tree, Node.HuffPComparator);
            Node hn1 = tree.get(0);
            tree.remove(hn1);
            Node hn2 = tree.get(0);
            tree.remove(hn2);
            Node node = new Node("blank", hn1.probability + hn2.probability, hn1, hn2);
            tree.add(node);
        }

        encode(tree.get(0));
        displayTree(tree.get(0));
        for (Node hn : hnodes)
            System.out.println(hn.symbol + ": " + hn.code);
        System.out.println();
        System.out.println("平均符号語長: " + acwl);

    }

    private static void encode(Node root) {
        if (root.left != null && root.right != null) {
            root.left.code += (root.code + "0");
            root.right.code += (root.code + "1");
            encode(root.left);
            encode(root.right);
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
        Collections.sort(hnodes, Node.HuffSymComparator);
    }
}