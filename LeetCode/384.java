/**
 * 为了得到随机分布，需要使用 Knuth shuffle 算法
 * 
 */
class Solution {

    int[] nums;

    public Solution(int[] nums) {
        this.nums = Arrays.copyOf(nums, nums.length);
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return Arrays.copyOf(nums, nums.length);
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] shuffled = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < nums.length; i++) {
            int j = i + (int)(Math.random() * (nums.length - i)); // 这里需要注意的是 i + rand() % (res.size() - i) 不能写成rand() % res.size()，虽然也能通过OJ，但是前面那种写法不是真正的随机分布
            int t = shuffled[i];
            shuffled[i] = shuffled[j];
            shuffled[j] = t;
        }
        return shuffled;
    }
}