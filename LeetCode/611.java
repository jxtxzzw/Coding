class Solution {
    // 第一眼暴力，显然估计不可行，于是考虑通过 a+b 来找 c，排序后二分
    // 但是还有更巧妙的方法，线性扫描
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int i = 0;
        while (i < nums.length && nums[i] == 0) {
            i++; // 跳过所有长度为 0 的线段，这些不可能组成三角形
        }
        while (i < nums.length) {
            int j = i + 1, k = i + 2;
            while (j < nums.length - 1) {
                while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                ans += k - j - 1;
                j++;
            }
            i++;
        }
        return ans;
    }
}
