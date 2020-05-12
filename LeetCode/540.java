class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {
                if (mid % 2 == 1) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                if (mid % 2 == 1) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[left];
    }
}
