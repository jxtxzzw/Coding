/*
   Hint #1  
One thing's for sure, we will only flip a zero if it extends an existing window of 1s. Otherwise, there's no point in doing it, right? Think Sliding Window!
    Hint #2  
Since we know this problem can be solved using the sliding window construct, we might as well focus in that direction for hints. Basically, in a given window, we can never have > K zeros, right?
   Hint #3  
We don't have a fixed size window in this case. The window size can grow and shrink depending upon the number of zeros we have (we don't actually have to flip the zeros here!).
   Hint #4  
The way to shrink or expand a window would be based on the number of zeros that can still be flipped and so on.
*/

import java.util.Stack;

public class LC {

    static public int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int ans = 0;
        int count = 0;
        while (left <= right && right < nums.length) {
            if (nums[right] == 1) {
                right++;
            } else if (count < k) {
                count++;
                right++;
            } else {
                ans = Math.max(ans, right - left);
                while (nums[left] == 1) {
                    left++;
                }
                if (count > 0) {
                    count--;
                } else {
                    right++;
                }
                left++;
            }
        }
        ans = Math.max(ans, right - left);
        return ans;
    }

    public static void main(String[] args) {
//        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
//        int k = 2;
//        int[] nums = {0,0,1,1,1,0,0};
//        int k = 0;
        int[] nums = {0,0,0,1};
        int k = 4;
        System.out.println(longestOnes(nums, k));
    }
}
