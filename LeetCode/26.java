class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int k = 0;
        int cursor = 1;
        while (cursor < nums.length) {
            if (nums[cursor] != nums[k]) {
                nums[++k] = nums[cursor];
            }
            cursor++;
        }
        return k + 1;
    }
}