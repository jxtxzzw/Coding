class Solution {
    public int jump(int[] nums) {
        int cur = 0;
        int far = nums[cur];
        int end = nums.length - 1;
        if (cur == end) {
            // Corner Case 如果已经在终点了就不需要计算了
            return 0;
        }
        int cnt = 1;
        while (cur + far < end) {
            int pos = -1;
            for (int i = cur + 1; i <= cur + far; i++) {
                // 如果跨越步数一样大，贪心到最后一个出现的，所以要加等号
                // 注意不是贪那个格子上能够跳的长度 nums[x]，而是贪能够跳到哪儿 nums[x] + x
                if (pos == -1 || nums[pos] + pos <= nums[i] + i) {
                    pos = i;
                }
            }
            far = nums[pos];
            cur = pos;
            cnt++;
        }
        return cnt;
    }
}
