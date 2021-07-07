class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int min = matrix[0][0], max = matrix[matrix.length - 1][matrix[0].length - 1];
        while (min < max) {
            // The following lines should be re-initialized in the loop
            int r = matrix.length - 1, c = 0; // LEFT BOTTOM
            int count = 0; // How many numbers are smaller than target
            int mid = min + (max - min) / 2;
            // search from the left bottom
            while (r >= 0 && c < matrix[0].length) {
                int candidate = matrix[r][c];
                if (candidate <= mid) { // Why <= not <: still need to keep track of the numbers
                    c++; // I need mid, but this one is candidate, so mid should be in the right side of c
                    count += r + 1;  // index is x, so there are x + 1 numbers smaller
                } else {
                    r--; // I need mid, but candidate is larger, so I need to go back one row
                }
            }
            if (count < k) {
                // I need larger number
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }
}
