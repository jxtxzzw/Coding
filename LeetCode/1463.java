class Solution {
    public int cherryPickup(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        // Use dynammic programming, define DP[i][j][k]: The maximum cherries that both robots can take starting on the ith row, and column j and k of Robot 1 and 2 respectively.
        int[][][] dp = new int[row][col][col];
        
        // 初始化
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < col; j++) {
                if (i == j) {
                    dp[row - 1][i][j] = grid[row - 1][i];
                } else {
                    dp[row - 1][i][j] = grid[row - 1][i] + grid[row - 1][j];
                }
            }
        }
        System.out.println(Arrays.deepToString(dp[row - 1]));
        
        // 状态转移
        for (int r = row - 2; r >= 0; r--) {
            for (int i = 0; i < col; i++) {
                for (int j = 0; j < col; j++) {
                    int R = -1;
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if (i + x >= 0 && i + x < col && j + y >= 0 && j + y < col) {
                                R = Math.max(R, dp[r + 1][i + x][j + y]);                            
                            }
                        }
                    }
                    R += grid[r][i];
                    if (i != j) {
                        R += grid[r][j];
                    }
                    
                    dp[r][i][j] = Math.max(dp[r][i][j], R);
                }
            }
            System.out.println(Arrays.deepToString(dp[r]));
        }
        
        
        // 结果
        // 起点只能是左右两个角，所以不需要遍历第 0 行找最大值
        return dp[0][0][col - 1];
        
    }
}
