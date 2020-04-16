class Trie {

    private TireNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TireNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TireNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.contains(c)) {
                node.put(c);
            }
            node = node.get(c);
        }
        node.setEnd();
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TireNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.contains(c)) {
                node = node.get(c);
            } else {
                return false;
            }
        }
        return node.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TireNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (node.contains(c)) {
                node = node.get(c);
            } else {
                return false;
            }
        }
        return true;
    }
}

class TireNode {
    private HashMap<Character, TireNode> links;
    private boolean end;


    public TireNode() {
        links = new HashMap<>();
        end = false;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd() {
        end = true;
    }

    public TireNode get(char c) {
        return links.get(c);
    }

    public void put(char c) {
        links.put(c, new TireNode());
    }

    public boolean contains(char c) {
        return links.containsKey(c);
    }
}