class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int r = 0, c = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int direction = 0;
        
        for (int x = 1; x <= n * n; x++) {
            matrix[r][c] = x;
            r += directions[direction][0];
            c += directions[direction][1];
            if (r >= n || c >= n || r < 0 || c < 0 || matrix[r][c] != 0) {
                r -= directions[direction][0];
                c -= directions[direction][1];
                direction = (direction + 1) % 4;
                r += directions[direction][0];
                c += directions[direction][1];
            }
            
        }
        return matrix;
    }
}
