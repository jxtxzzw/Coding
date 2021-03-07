class Trie {

    private final TireNode root;

    private final ArrayList<TireNode> words;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TireNode();
        words = new ArrayList<TireNode>();
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
        node.setCache(word);
        words.add(node);
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

    public HashSet<TireNode> getLeaves() {
        HashSet<TireNode> leaves = new HashSet<>();
        for (TireNode tn : words) {
            if (tn.isLeaf()) {
                leaves.add(tn);
            }
        }
        return leaves;
    }
}

class TireNode {
    private final HashMap<Character, TireNode> links;
    private boolean end;
    private String cache;


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

    public void setCache(String word) {
        cache = word;
    }

    public String getCache() {
       return cache;
    }

    public boolean isLeaf() {
        return links.keySet().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TireNode)) {
            return false;
        }
        TireNode tireNode = (TireNode) o;
        return cache != null && cache.equals(tireNode.cache);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cache);
    }

    @Override
    public String toString() {
        return cache;
    }
}

// 核心思想：反向插入字典树

class Solution {
    public int minimumLengthEncoding(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            StringBuilder sb = new StringBuilder(word);
            String reverse = sb.reverse().toString();
            trie.insert(reverse);
        }

        int ans = 0;
        for (TireNode tn : trie.getLeaves()) {
            ans += tn.getCache().length() + 1;
        }
        // System.out.println(trie.getLeaves());
        return ans;
    }
}
