class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int longest = 0;
        int index = 0;
        while (index < nums.length) {
            longest = Math.max(longest, index + nums[index]);
            if (longest >= nums.length - 1) {
                return true;
            }
            if (longest > index) {
                index++;
            } else {
                return false;
            }
        }
        return false;
    }
}
