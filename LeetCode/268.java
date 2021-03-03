class Solution {
    public int missingNumber(int[] nums) {
        // 线性时间复杂度+常数空间复杂度=用数学方法
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        
        return (nums.length + 1) * nums.length / 2 - sum;
        
    }
}
