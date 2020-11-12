// // 全排列就是回溯法，这题的难点在于去掉重复元素，这个有一个技巧：对原数组排序，使得相同元素都在相邻位置，然后保证每次取相同数字的时候必须取最左边的开始，通过 if (visit[i] || (i > 0 && nums[i] == nums[i - 1] && !visit[i - 1])) 来跳过不符合条件的排列

class Solution {
    boolean[] visit;
    List<List<Integer>> result = new ArrayList<>();
    int[] nums;
    public List<List<Integer>> permuteUnique(int[] nums) {
        this.nums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(this.nums);
        visit = new boolean[nums.length];
        ArrayList<Integer> current = new ArrayList<>();
        backtrack(0, current);
        return result;
    }

    public void backtrack(int idx, ArrayList<Integer> current) {
        if (current.size() == nums.length) {
            // make a copy
            result.add(new ArrayList<>(current));
        }
        // the key point is backtrack
        // try put each element, if it is not visited, to current[idx]
        for (int i = 0; i < nums.length; i++) {
            // sort the nums[], and put equal elements only from the left to the right
            if (visit[i] || (i > 0 && nums[i] == nums[i - 1] && !visit[i - 1])) {
                continue;
            }
            current.add(nums[i]);
            visit[i] = true;
            backtrack(idx + 1, current);
            visit[i] = false;
            current.remove(idx);
        }
    }
}