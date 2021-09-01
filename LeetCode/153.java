class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[l] <= nums[mid]) {
                if (nums[mid] <= nums[r - 1]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            } else {
                r = mid + 1;
                l = l + 1;
            }
        }
        return nums[l];
    }
}