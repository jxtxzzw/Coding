class Solution {
    private final int INIT = -1;
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        int min = nums[0], max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // 核心1：整个距离是 max - min，分成 n - 1 段，每一段的平均是 capacity，则必有最大间距大于这个值
        int capacity = (max - min) / (nums.length - 1);
        if (capacity == 0) {
            capacity = 1; // CORNER CASE
        }
        int count = (max - min) / capacity + 1; // 向下取整会导致余数，余数需要一个单独的桶

        // 不需要整个有序，只需要每个桶最大和最小值
        int[] mins = new int[count];
        int[] maxs = new int[count];
        for (int i = 0; i < count; i++) {
            mins[i] = INIT;
            maxs[i] = INIT;
        }

        for (int num : nums) {
            int id = (num - min) / capacity;
            if (mins[id] == INIT) {
                mins[id] = num;
                maxs[id] = num;
            } else {
                mins[id] = Math.min(num, mins[id]);
                maxs[id] = Math.max(num, maxs[id]);
            }
        }

        // 核心2：最大间距不可能出现在一个桶的内部，一个桶的内部最大差距是 capacity = (max - min) / (nums.length - 1)，而我们知道最大间距一定大于这个平均值

        int ans = 0; // 初始值 0 因为此时最坏情况是 min = max
        int prev = 0, current = 1;
        while (current < count) {
            while (mins[current] == INIT) {
                current++;
            }
            while (prev < current && mins[prev] == INIT) {
                prev++;
            }
            ans = Math.max(ans, mins[current] - maxs[prev]);
            current++;
            prev++;
        }

        return ans;

    }
}
