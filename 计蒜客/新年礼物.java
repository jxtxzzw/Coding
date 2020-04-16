import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    private static int[] extendedKMP(String t) {
        int n = t.length();
        int[] next = new int[n];
        next[0] = n;
        if (n == 1) {
            // 注意 corner case，否则会出现数组越界
            return next;
        }
        int p = 0;
        while (p < n - 1 && t.charAt(p) == t.charAt(p + 1)) {
            p++;
        }
        next[1] = p;
        int k = 1, l;
        for (int i = 2; i < n; i++) {
            p = k + next[k] - 1;
            l = next[i - k];
            if (i + l - 1 < p) {
                next[i] = l;
            } else {
                int j = p - i + 1;
                if (j < 0) {
                    j = 0;
                }
                while (i + j < n && t.charAt(i + j) == t.charAt(j)) {
                    j++;
                }
                next[i] = j;
                k = i;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        Trie t = new Trie();
        int[] dp = new int[n];
        // 赋初值为 1，因为至少自己是满足条件的
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            String s = in.next();
            int[] next = extendedKMP(s);
            t.insert(s, next, i, dp);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
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
    public void insert(String word, int[] next, int index, int[] dp) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.contains(c)) {
                node.put(c);
            }
            node = node.get(c);
            if (node.isEnd()) {
                // 当满足 isEnd() 这个条件的时候，word 到当前位置为止的前缀是一个已经存在的子串 xi
                // 所以前缀的性质满足，只需要检查是不是后缀
                // 这个前缀的长度 len = i + 1
                int len = i + 1;
                // 如果距离最后为 len 的那个位置上，它满足的前缀长度恰好是 len，那么就是满足的
                if (next[word.length() - len] == len) {
                    dp[index] = Math.max(dp[index], dp[node.getIndex()] + 1);
                }
            }
        }
        node.setEnd(index);
    }
}

class TrieNode {
    private HashMap<Character, TrieNode> links;
    private boolean end;
    private int index;

    public TrieNode() {
        links = new HashMap<>();
        end = false;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(int index) {
        end = true;
        this.index = index;
    }

    public int getIndex() {
        return index;
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

}