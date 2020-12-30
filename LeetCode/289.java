// 难点在于 in-place，技巧是用更多的状态来表示从活到死、从死到活、从活到活、从死到死
class Solution {
    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // neighbours
                int live = 0;
                int die = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if (x == 0 && y == 0) {
                            continue;
                        }
                        if (row + x < 0 || row + x >= rows || col + y < 0 || col + y >= cols) {
                            continue;
                        }
                        if (board[row + x][col + y] == 1 || board[row + x][col + y] == 2) {
                            live++;
                        }
                        if (board[row + x][col + y] == 0 || board[row + x][col + y] == 4) {
                            die++;
                        }
                    }
                }
                if (board[row][col] == 1) {
                    if (live < 2) {
                        // Rule 1: Under-population
                        board[row][col] = 2; // live to die
                    }
                    if (live == 2 || live == 3) {
                        // Rule 2: Lives on to the next generation
                    }
                    if (live > 3) {
                        // Rule 3: Over-population
                        board[row][col] = 2; // live to die
                    }
                } 
                if (board[row][col] == 0) {
                    if (live == 3) {
                        // Rule 4: Reproduction
                        board[row][col] = 3; // die to live
                    }
                }
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == 2) {
                    board[row][col] = 0;
                }
                if (board[row][col] == 3) {
                    board[row][col] = 1;
                }
            }
        }
    }
}
