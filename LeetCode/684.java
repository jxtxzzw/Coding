class Solution {
    
    private int[] father;
    
    private int find(int x) {
        return x == father[x] ? x : (father[x] = find(father[x]));
    }
    
    private void merge(int x, int y) {
        father[find(x)] = find(y);
    }
    
    private boolean same(int x, int y) {
        return find(x) == find(y);    
    }
    
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        father = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            father[i] = i;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            if (same(x, y)) {
                ans = i;
            } else {
                merge(x, y);
            }
        }
        assert(ans != 0);
        return edges[ans];
    }
}
