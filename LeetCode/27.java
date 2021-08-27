class Solution {
    public int removeElement(int[] nums, int val) {
        int len = 0;
        
        for (int cursor = 0; cursor < nums.length; cursor++) {
            if (nums[cursor] != val) {
                nums[len++] = nums[cursor];
            }
        }
        
        return len;
    }

}
