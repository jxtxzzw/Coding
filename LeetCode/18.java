public List<List<Integer>> twoSum(int[] nums, int target, int start) {
    List<List<Integer>> res = new ArrayList<>();
    int lo = start, hi = nums.length - 1;

    while (lo < hi) {
        int currSum = nums[lo] + nums[hi];
        if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
            ++lo;

        } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
            --hi;
        }
        else {
            res.add(Arrays.asList(nums[lo++], nums[hi--]));
        }
    }

    return res;
}

public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
    List<List<Integer>> res = new ArrayList<>();
    if (start == nums.length) {
        return res;
    }
    if (nums[start] * k > target || nums[nums.length - 1] * k < target) {
        return res;
    }
    if (k == 2) {
        return twoSum(nums, target, start);
    }
    for (int i = start; i < nums.length; i++) {
        if (i > start && nums[i] == nums[i - 1]) {
            continue;
        }
        List<List<Integer>> sub = kSum(nums, target - nums[i], i + 1, k - 1);
        for (List<Integer> s : sub) {
            ArrayList<Integer> a = new ArrayList<>();
            a.add(nums[i]);
            a.addAll(s);
            res.add(a);
        }
    }
    return res;
}

public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    return kSum(nums, target, 0, 4);
}