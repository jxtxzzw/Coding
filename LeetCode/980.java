class Solution {
     int[][] grid;
    int walked = 0;
    int ans = 0;
    int target = 0;

    int sx, sy, tx, ty;
    
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    target++;
                } else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 2) {
                    tx = i;
                    ty = j;
                }
            }
        }
        dfs(sx, sy);
        return ans;
    }

    void dfs(int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] == -1) {
            return;
        }
        if (x == tx && y == ty) {
            if (walked == target) {
                ans++;
            }
            return;
        }
        if (x != sx || y != sy) {
            walked++;
        }
        grid[x][y] = -1;
        dfs(x + 1, y);
        dfs(x, y + 1);
        dfs(x - 1, y);
        dfs(x, y - 1);
        if (x != sx || y != sy) {
            walked--;
        }
        grid[x][y] = 0;
    }
}
