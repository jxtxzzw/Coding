// 我们要从头尾找出一些数字，相加为 x
// 意思是从数组中间取出一段连续的，令其为 sum - x，其中 sum 是数组元素和
// 中间找出一段连续的，和为 sum - x
// 由于必须是连续数组，所以不需要 DP，直接双指针（滑动窗口）

class Solution {
    public int minOperations(int[] nums, int x) {
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 计算数组和
            sum += nums[i];
        }
        
        // 找一个 l 到 r 的区间，令它们的和为 sum - x
        int target = sum - x;
        System.out.println("sum=" + sum + ", x=" + x + ", target=" + target);
        if (target == 0) {
            // CORNER CASE
            return nums.length;
        }
        int l = 0;
        int r = 1;
        int acc = nums[0];
        int ans = Integer.MAX_VALUE;
        while (l <= r && r < nums.length) {
            if (l == r) {
                acc += nums[r];
                r++;
            }
            if (r >= nums.length) {
                break;
            }
            if (acc == target) {
                ans = Math.min(ans, nums.length - (r - l));
                acc += nums[r];
                r++;
                acc -= nums[l];
                l++;
            }
            if (r >= nums.length) {
                break;
            }
            if (acc < target) {
                acc += nums[r];
                r++;
            } else if (acc > target){
                acc -= nums[l];
                l++;
            }
            // System.out.println("l=" + l + ", r=" + r + ", acc=" + acc + ", ans=" + ans);
        }
        if (acc == target) {
            ans = Math.min(ans, nums.length - (r - l));
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
