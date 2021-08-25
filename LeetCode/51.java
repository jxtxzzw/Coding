class Solution {
    int MAXN = 10;
    char QUEEN = 'Q', EMPTY = '.';

    int n;
    char[][] Q = new char[MAXN][MAXN];
    List<List<String>> solutions = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Q[i][j] = EMPTY;
            }
        }
        this.n = n;
        queen(0);
        return solutions;
    }

    private void queen(int row) {
        if (row == n) {
            ArrayList<String> solution = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; ++j) {
                    sb.append(Q[i][j]);
                }
                solution.add(sb.toString());
            }
            solutions.add(solution);
        }

        for (int col = 0; col < n; col++) {
            if (isValid (row, col) ) {
                Q[row][col] = QUEEN;
                queen(row + 1);
                Q[row][col] = EMPTY;
            }
        }
    }

    private boolean isValid (int row, int col) {
        for (int j = 0; j < n; j++)
            if (Q[row][j] == QUEEN && col != j)
            { return false; }

        for (int i = 0; i < n; i++)
            if (Q[i][col] == QUEEN && row != i)
            { return false; }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (Q[i][j] == QUEEN)
            { return false; }

        for (int i = row + 1, j = col + 1; i < n && j < n; i++, j++)
            if (Q[i][j] == QUEEN)
            { return false; }

        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++)
            if (Q[i][j] == QUEEN)
            { return false; }

        for (int i = row + 1, j = col - 1; i < n && j >= 0; i++, j--)
            if (Q[i][j] == QUEEN)
            { return false; }

        return true;
    }
}
