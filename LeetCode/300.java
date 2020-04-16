/* 方法 1 */

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}

/* 方法 2 */

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        ArrayList<Integer> dp = new ArrayList<>();
        dp.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (dp.get(dp.size() - 1) < nums[i]) {
                dp.add(nums[i]);
            } else {
                int searchResult = Collections.binarySearch(dp, nums[i]);
                if (searchResult < 0) {
                    int insertionPoint = -(searchResult + 1);
                    dp.set(insertionPoint, nums[i]);
                }
            }
        }
        return dp.size();
    }
}