import java.util.*;

public class Node {
    String symbol;
    double probability;
    String code;
    Node left;
    Node right;

    Node(String s, double p, Node lt, Node rt) {
        this.symbol = s;        //アルファベット
        this.probability = p;   //出現確率
        this.left = lt;         //左の木
        this.right = rt;        //右の木
        this.code = "";         //符号
    }

    public static class compProb implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            if (n1.probability == n2.probability) {
                return 0;
            } else {
                return n1.probability - n2.probability > 0 ? 1 : -1;
            }
        }
    }

    public static class compSym implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.symbol.compareTo(n2.symbol);
        }
    }
}