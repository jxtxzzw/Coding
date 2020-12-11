// 双指针，移动的时候决定要不要这个数
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int i = 1;
        int j = 1;
        int count = 1;        
        while (j < nums.length) {
            if (nums[j - 1] == nums[j]) {
                count++;
            } else {
                count = 1;
            }
            if (count <= 2) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        return i;
    }
}
