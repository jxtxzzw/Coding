public int partitionDisjoint(int[] nums) {
    /** Instead of checking whether all(L <= R for L in left for R in right), let's check whether max(left) <= min(right). **/
    int n = nums.length;
    int[] leftMax = new int[n];
    int[] rightMin = new int[n];

    /** max(A[:k]) = max(max(A[:k - 1]), A[k]) **/
    leftMax[0] = nums[0];
    for (int i = 1; i < n; i++) {
        leftMax[i] = Math.max(leftMax[i - 1], nums[i]);
    }

    rightMin[n - 1] = nums[n - 1];
    for (int i = n - 2; i >= 0; i--) {
        rightMin[i] = Math.min(nums[i], rightMin[i + 1]);
    }

    for (int i = 1; i < n; i++) {
        if (leftMax[i - 1] <= rightMin[i]) {
            return i;
        }
    }
    return 0;
}
