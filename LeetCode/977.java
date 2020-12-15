// 双指针
class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int cursor = nums.length - 1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] < nums[right] * nums[right]) {
                ans[cursor] = nums[right] * nums[right];
                right--;
            } else {
                ans[cursor] = nums[left] * nums[left];
                left++;
            }
            cursor--;
        }
        return ans;
    }
}
