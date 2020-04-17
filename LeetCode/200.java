class Solution {

    public int numIslands(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }

    private void dfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction: directions) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            if (isValid(grid, newI, newJ)) {
                dfs(grid, newI, newJ);
            }
        }
    }
    
    private boolean isValid(char[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length && grid[i][j] == '1';
    }
}
