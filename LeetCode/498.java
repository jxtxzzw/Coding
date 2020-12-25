class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return new int[0];
        }
        int cols = matrix[0].length;
        int[] ans = new int[rows * cols];
        int row = 0, col = 0;
        int x = -1, y = 1;
        int idx = 0;
        while (row != rows - 1 || col != cols - 1) {
            ans[idx++] = matrix[row][col];
            // System.out.println(Arrays.toString(ans));
            row += x;
            col += y;
            
            // 就是 9 种情况，考虑清楚
            if (row < 0 && col >= cols) {
                row = 1;
                col = cols - 1;
            } else if (row < 0 && col < cols) {
                row = 0;
            } else if (col < 0 && row >= rows) {
                row = rows - 1;
                col = 1;
            } else if (col < 0 && row < rows) {
                col = 0;
            } else if (col >= cols && row < rows) {
                row += 2;
                col = cols - 1;
            } else if (row >= rows && col < cols) {
                col += 2;
                row = rows - 1;
            } else {
              continue;  
            }
            x = -x;
            y = -y;
        }
        ans[idx++] = matrix[rows - 1][cols - 1];
        return ans;
    }
}
