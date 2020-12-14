// 这种题一看贪心就不成立，那就 DP
class Solution {
    
    private int[] nums;
    
    private int score(int i) {
        if (i < 0 || i >= nums.length) {
            return 1;
        } else {
            return nums[i];
        }
    }
        
    public int maxCoins(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        this.nums = nums;
        // 区间 DP 记录从 i 到 j 的最大值
        int[][] dp = new int[n][n];
        
        // 初始条件是只打爆自己
        for (int i = 0; i < n; i++) {
            dp[i][i] = score(i - 1) * score(i) * score(i + 1);
        }
        
        System.out.println(Arrays.deepToString(dp));
        
        // 从长度为 1 的区间开始遍历
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                // 尝试不同的切分
                // 这里容易错：i 和 j 取等号
                for (int k = i; k <= j; k++) {
                    int left = k == 0 ? 0 : dp[i][k - 1];
                    int right = k == n - 1 ? 0 : dp[k + 1][j];
                    // 注意打爆 k 的话，k - 1 和 k + 1 已经不能用了，两边是 i - 1 和 j + 1
                    int mid = (score(i - 1) * score(k) * score(j + 1));
                    // 状态转移
                    dp[i][j] = Math.max(dp[i][j], left + mid + right);
                }
            }
        }
        return dp[0][n - 1];
    }
}