class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length;
        int candidate = nums[0];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    assert i + 1 < n;
                    candidate = nums[i + 1];
                }
            }
        }
        return candidate;
    }
}
