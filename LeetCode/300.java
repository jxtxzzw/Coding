class Solution {
    public int lengthOfLIS(int[] nums) {
        return lengthOfLIS_02(nums);
    }
    
    public int lengthOfLIS_01(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) { // this is i, not nums.length
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int lengthOfLIS_02(int[] nums) {
        int[] tails = new int[nums.length];
        int ans = 0;
        for (int d : nums) {
            int l = 0, r = ans;
            while (l < r) {
                int mid = (l + r) / 2;
                if (d > tails[mid]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            tails[l] = d;
            if (r == ans) {
                ans++;
            }
        }
        return ans;
    }
}
