import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Trie tree = new Trie();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            try {
                tree.insert(s);
            } catch (Exception e) {
                System.out.println("Bug!");
                System.exit(0);
            }
        }
        System.out.println("Good Luck!");
    }

}

class Trie {

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) throws Exception {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.contains(c)) {
                node.put(c);
            }
            node = node.get(c);
            if (node.isEnd()) {
                throw new Exception("BUG");
            }
        }
        node.setEnd();
        if (node.hasChild()) {
            throw new Exception("BUG");
        }
    }
}

class TrieNode {
    private HashMap<Character, TrieNode> links;
    private boolean end;

    public TrieNode() {
        links = new HashMap<>();
        end = false;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd() {
        end = true;
    }

    public TrieNode get(char c) {
        return links.get(c);
    }

    public void put(char c) {
        links.put(c, new TrieNode());
    }

    public boolean contains(char c) {
        return links.containsKey(c);
    }

    public boolean hasChild() {
        return !links.keySet().isEmpty();
    }

}