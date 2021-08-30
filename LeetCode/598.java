class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        // 一开始以为是树状数组，其实不需要，每次都是从左上角开始加，每次加一，那么最大的数字一定是在左上角开始的某一个矩形，所以始终记录最小长、宽即可

        int minRow = m, minCol = n;
        for (int[] op : ops) {
            minRow = Math.min(minRow, op[0]);
            minCol = Math.min(minCol, op[1]);
        }

        return minCol * minRow;
    }
}
