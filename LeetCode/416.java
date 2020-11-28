class Solution {
    // 当贪心不成立时，就要考虑动态规划了
    public boolean canPartition(int[] nums) {
        int length = nums.length;
        int sum = 0;
        int maxNumber = 0;
        for (int i: nums) {
            if (i > maxNumber) {
                maxNumber = i;
            }
            sum += i;
        }
        int target = sum / 2;
        // 和为奇数，或者最大值大于总和的一半，直接 false
        if (sum % 2 != 0 || maxNumber > target) {
            return false;
        }
        // 类似于 0-1 背包问题，把背包内元素和不大于 x 改成背包内元素和为 sum / 2
        // dp[i][j] 表示前 i 个元素能不能有一种方案使得和为 j
        boolean[][] dp = new boolean[length][target + 1];
        // 初始条件
        for (int i = 0; i < length; i++) {
            // 可以不取，所以前 i 个元素存在一种取法使得和为 0
            dp[i][0] = true;
        }
        // 也可以取，那 dp[0][nums[0]] 就是取 true
        dp[0][nums[0]] = true;
        // 状态转移，从 i = 1 开始
        for (int i = 1; i < length; i++) {
            for (int j = 0; j <= target; j++) {
                // 首先一定是 nums[i] 不取，则 dp[i][j] = d[i - 1][j]
                dp[i][j] = dp[i - 1][j];
                // 如果 nums[i] <= j，那么可以取 nums[i]
                if (nums[i] <= j) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        // 找到是否存在一个 i 使得 dp[i][target] 为 true
        for (int i = 0; i < length; i++) {
            if (dp[i][target]) {
                return true;
            }
        }
        return false;
    }
}
