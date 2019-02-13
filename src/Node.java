import java.util.Comparator;

public class Node {
    String symbol;
    double probability;
    String code;
    Node left;
    Node right;

    Node(String s, double p, Node lt, Node rt) {
        symbol = s;
        probability = p;
        left = lt;
        right = rt;
        code = "";
    }

    public static Comparator<Node> HuffSymComparator = new Comparator<Node>() {
        public int compare(Node h1, Node h2) {
            String HuffSym1 = h1.symbol;
            String HuffSym2 = h2.symbol;

            return HuffSym1.compareTo(HuffSym2);
        }
    };

    public static Comparator<Node> HuffPComparator = new Comparator<Node>() {
        public int compare(Node h1, Node h2) {
            double p1 = h1.probability;
            double p2 = h2.probability;

            return (int) (10000 * p1 - 10000 * p2);
        }
    };
}