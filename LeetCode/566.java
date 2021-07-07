class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if (r * c != mat.length * mat[0].length) {
            return mat;
        }
        int[][] m = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int id = i * c + j;
                int x = id / mat[0].length;
                int y = id % mat[0].length;
                m[i][j] = mat[x][y];
            }
        }
        return m;
    }
}
