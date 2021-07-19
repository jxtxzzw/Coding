class Solution {
public:
    int rob(vector<int>& nums) {
        const int k = nums.size();
        int ans = 0;
        if (k == 0) {
            ans = 0;
        } else if (k == 1) {
            ans = nums[0];
        } else {
            int dp[k];
            for (int i = 0; i < k; i++) {
                dp[i] = 0;
            }

            dp[0] = nums[0];
            dp[1] = max(nums[0], nums[1]);

            for (int i = 2; i < k; i++) {
                dp[i] = max(dp[i - 1], dp[i - 2] + nums[i]);
            }

            ans = dp[k - 1];
        }
        return ans;
    }
};
