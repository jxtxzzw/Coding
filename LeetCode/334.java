class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        // 很巧妙的思想，直接保留最小的 min 和最小的 mid
        // 我们不是要找一个出来，只需要判断是不是存在
        // 所以其实不需要保留“是哪一个”这个信息，那只要 min 存在，就可以证明存在
        // 虽然我们已经没有办法知道确切的是哪几个数字
        int min = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= min) {
                min = nums[i];
            } else if (nums[i] <= mid) {
                mid = nums[i];
            } else if (nums[i] > mid){
                // 存在，就是 true，我们不需要统计一共有多少个
                return true;
            }
        }
        // 不存在
        return false;
    }
}
