class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return true; // found
            }
            if (nums[right - 1] < nums[mid]) {
                // [left] is sorted
                if (target >= nums[left] && target <= nums[mid]) {
                    // binary search the left part
                    right = mid;
                } else {
                    left = mid;
                }
            } else if (nums[right - 1] > nums[mid]) {
                // [right] is sorted
                if (target >= nums[mid] && target <= nums[right - 1]) {
                    // binary search the right part
                    left = mid;
                } else {
                    right = mid;
                }
            } else {
                // nums[right] == nums[mid], we do not know which part is sorted because of the duplicated elements
                right--;
            }
        }
        return false; // not found
    }
}
