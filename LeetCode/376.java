class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int[] diff = new int[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            diff[i - 1] = nums[i] - nums[i - 1];
        }
        int i = 0;
        while (i < diff.length && diff[i] == 0) {
            i++;
        }
        if (i == diff.length) {
            return 1;
        }
        int prev = diff[i];
        int ans = 2;
        while (i < diff.length) {
            if (prev * diff[i] < 0) {
                ans++;
                prev = diff[i];
            }
            i++;
        }
        return ans;
    }
}
