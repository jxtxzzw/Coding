class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int r = 0; r < board.length; r++) {
            boolean[] filled = new boolean[10];
            for (int c = 0; c < board[r].length; c++) {
                char n = board[r][c];
                if (n == '.') {
                    continue;
                }
                if (filled[n - '0']) {
                    return false;
                } else {
                    filled[n - '0'] = true;
                }
            }
        }
        for (int c = 0; c < board[0].length; c++) {
            boolean[] filled = new boolean[10];
            for (int r = 0; r < board.length; r++) {
                char n = board[r][c];
                if (n == '.') {
                    continue;
                }
                if (filled[n - '0']) {
                    return false;
                } else {
                    filled[n - '0'] = true;
                }
            }
        }
        for (int r = 0; r < board.length; r += 3) {
            for (int c = 0; c < board[0].length; c += 3) {
                boolean[] filled = new boolean[10];
                for (int i = 0; i < 9; i++) {
                    int x = (r + i / 3);
                    int y = (c + i % 3);
                    char n = board[x][y];
                    if (n == '.') {
                        continue;
                    }
                    if (filled[n - '0']) {
                        return false;
                    } else {
                        filled[n - '0'] = true;
                    }
                }
            }
        }
        return true;
    }
}
