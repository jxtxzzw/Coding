class Solution {
    public int smallestRangeII(int[] A, int K) {
        if (A.length == 1) {
            return 0;
        }
        // 排序后，只需要对小的数字加 K，对大的数字减 K，才有可能让极差最小
        // 如果同时加 K 或者同时减 K，极差不变，如果小的数字减、大的数字加，极差反而变大
        Arrays.sort(A);
        int ans = A[A.length - 1] - A[0]; // 全局最大，全局最小
        for (int i = 0; i < A.length - 1; i++) {
            int max = Math.max(A[i] + K, A[A.length - 1] - K); // 只有可能 A[i] + K 成为新的最大，大过原来的最大
            int min = Math.min(A[i + 1] - K, A[0] + K); // 只有可能 A[i + 1] - K 成为新的最小
            ans = Math.min(max - min, ans);
        }
        return ans;
    }
}
