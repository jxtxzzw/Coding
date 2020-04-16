class Solution {

    private Trie trie = new Trie(); // 前缀树
    private HashSet<String> result = new HashSet<>(); // 用 Set 避免重复添加（去重）

    public List<String> findWords(char[][] board, String[] words) {
        for (String word : words) {
            trie.insert(word);
        }
        int rows = board.length;
        int cols = board[0].length;
        //从每个位置开始遍历，查询前缀 prefix 是否符合要求
        String prefix = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, i, j, prefix);
            }
        }
        return new ArrayList<>(result); // 最后把 Set 转化为 List 返回
    }

    private void dfs(char[][] board, int i, int j, String prefix) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return; // 越界
        }
        char c = board[i][j]; // 记录当前位置
        if (c != ' ') {
            // 如果没访问过当前位置，设置为已访问（置空格），然后 append 到 prefix（prefix += c）
            board[i][j] = ' ';
            prefix += c;
            // 如果存在前缀，继续，否则没必要往下了，直接 return
            if (trie.startsWith(prefix)) {
                // 如果该前缀恰好也是一个单词的结束，则加入 result
                if (trie.search(prefix)) {
                    result.add(prefix);
                }
                // 深搜
                dfs(board, i, j + 1, prefix);
                dfs(board, i + 1, j, prefix);
                dfs(board, i - 1, j, prefix);
                dfs(board, i, j - 1, prefix);
            }
            // 还原棋盘当前位置
            board[i][j] = c;
        }
    }
}

class Trie {

    private TireNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TireNode();
    }

    /**
     * Inserts a word into the trie.
     */
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

    /**
     * Returns if the word is in the trie.
     */
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

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
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